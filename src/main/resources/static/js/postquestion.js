
const app = new Vue({
	el:"#vue_booksearch_el",
	data(){
		return{
			keyword:"",
			items:[],
			selectedBook:{
				isbn13:"",
				thumbnail:""
			}
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
		selectBook(item){
			this.selectedBook = item;
		}
	}
});

