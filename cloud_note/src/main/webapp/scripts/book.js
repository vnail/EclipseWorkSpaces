function loadUserBooks(){
			//获取Cookie中的userId
			var userId=getCookie("userId");
			
			//userId是否有效
			if(userId==null){
				window.location.href("log_in.html");
			}else{
				//alert(userId);
				//请求bookds数据
				$.ajax({
					url:path+"/book/loadBookds.do",
					type:"post",
					data:{"userId":userId},
					dataType:"JSON",
					success:function(result){
						if(result.status==0){
							var books=result.data;
							for(var i=0;i<books.length;i++){
								var bookId=
									books[i].cn_notebook_id;
								
								var bookName=
									books[i].cn_notebook_name;
								//创建一个笔记本列表的li元素
								creatBookLi(bookId,bookName);
								 
							}
							
						}
						else if(result.status==1){}
						
					},
					error:function(){
						alert("请求bookds数据失败");
					}
				});
				
			}

		
		   }

//创建一个笔记本li元素
function creatBookLi(bookId,bookName){
	var sli="";
	sli+='<li class="online">';
	sli+='<a>'
	sli+='<i class="fa fa-book" title="online" rel="tooltip-bottom">';
	sli+='</i>';
	sli+=bookName;
	sli+='</a></li>';
	
	//将sli字符串转换成jQuery对象li元素
	var $li=$(sli);
	//将bookId值与jQuery对象绑定
	$li.data("bookId",bookId)
	//将li元素添加到笔记本ul列表区
	$("#book_ul").append($li);
	
}












