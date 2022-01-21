
const app = new Vue({
	el:"#vue_el",
	data(){
		return{
			keyword:"",
			items:[],
			isbn13:""
		}
	},
	methods:{
		getResult(keyword){
			const url = new URL("/postquestion/booksearch", document.baseURI);
			url.searchParams.append("keyword", keyword);
			fetch(url)
				.then(response => response.json())
				.then(json => {this.items = json;console.log(json);});
		},
		selectBook(isbn13){
			this.isbn13 = isbn13;
		}
	}
});

