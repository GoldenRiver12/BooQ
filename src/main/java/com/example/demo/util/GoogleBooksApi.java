package com.example.demo.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.model.Book;
import com.example.demo.model.googlebooksapi.GbaResponse;
import com.example.demo.model.googlebooksapi.Item;
import com.example.demo.repository.BookRepositoryImpl;
import com.google.gson.Gson;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.val;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GoogleBooksApi {
	
	/**
	 * Google books APIのベースURL
	 */
	private static final String GOOGLE_BOOKS_API_URL_BASE = "https://www.googleapis.com/books/v1/volumes";
	
	/**
	 * Google books API経由で本の一覧を取得する
	 * @param queryParameters APIに渡すクエリパラメータ一覧（変数名->値）
	 * @return 検索結果の本リスト
	 * @throws IOException
	 */
	public static List<Book> getBookListFromGba(Map<String, String> queryParameters) throws IOException{
		return getBookList(getResponseFromGba(queryParameters));
	}
	
	/**
	 * Google books APIにリクエストを送信し、返却されたJSONをPOJOとして返す
	 * @param queryParameters APIに渡すクエリパラメータ一覧（変数名->値）
	 * @return レスポンスのJSONをPOJOに変換したもの
	 * @throws IOException
	 */
	private static GbaResponse getResponseFromGba(Map<String, String> queryParameters) throws IOException {
		String queryString = queryParameters.entrySet().stream()
				.map(e -> String.format("%s=%s", e.getKey(), e.getValue())).collect(Collectors.joining("&"));

		Gson gson = new Gson();
		
		String response = getResponseFrom(String.format("%s?%s", GOOGLE_BOOKS_API_URL_BASE, queryString));
		
		return gson.fromJson(response, GbaResponse.class);
	}
	
	/**
	 * 引数のURLにGETリクエスト送信を行い、レスポンスボディを返却する
	 * 
	 * @param url
	 * @return レスポンスボディ
	 * @throws IOException
	 */
	private static String getResponseFrom(String url) throws IOException {
		Request request = new Request.Builder().url(url).get().build();

		OkHttpClient client = new OkHttpClient();

		Response response = client.newCall(request).execute();

		return response.body().string();
		
	}
	
	/**
	 * Google books API のレスポンスから本の一覧を取得する
	 * ただし、ISBN13が割り当てられていない書籍は除く
	 * @param response
	 * @return 本の一覧
	 */
	public static List<Book> getBookList(GbaResponse response){
		return response.getItems().stream()
			.map(GoogleBooksApi::convertItemToBook)
			.flatMap(Optional::stream)
			.collect(Collectors.toList());
	}
	
	/**
	 * ItemモデルからBookモデルへの変換を行う
	 * ISBN13が割り当てられていないItemに対してはOptional.empty()を返す
	 * @param item
	 * @return
	 */
	public static Optional<Book> convertItemToBook(Item item){
		val isbn13Op = getIsbn13(item);
		val title = item.getVolumeInfo().getTitle();
		return isbn13Op.map(isbn13 -> new Book(
				isbn13,
				title
				));
	}
	
	/**
	 * ItemモデルからISBN13を取得する
	 * ISBN13が割り当てられていないItemに対してはOptional.empty()を返す
	 * @param item
	 * @return
	 */
	public static Optional<String> getIsbn13(Item item) {
		return item.getVolumeInfo().getIndustryIdentifiers().stream().filter(ii -> ii.getType().equals("ISBN_13"))
				.findAny().map(ii -> ii.getIdentifier());
	}
	
	public static void main(String[] args) {
		BookRepositoryImpl test = new BookRepositoryImpl();
		try {
			test.findByKeyword("psycology")
				.stream()
				.forEach(System.out::println);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
