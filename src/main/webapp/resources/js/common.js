function paginateSetting(totalCount, pageSize, pageNo, procFlag){
	var inHtml = "";
	if (procFlag != null && procFlag != "" && procFlag == 'D') {
		if (totalCount != 0) {
			var pagingCnt = 0;
			
			if (parseInt(totalCount%pageSize) == 0) {
				pagingCnt = parseInt(totalCount/pageSize);
			} else {
				pagingCnt = parseInt(totalCount/pageSize)+1;
			}
			
			if (pagingCnt == 1) {
				inHtml += ' <a href="#" class="pg_num selected">1</a> ';
			}else {
				if (pageNo != 1) {
					inHtml += ' <a href="#" class="btn btn_direction first" onclick="serch(1)"><span class="srOnly">처음으로</span></a> ';
					inHtml += ' <a href="#" class="btn btn_direction prev" onclick="serch('+(pageNo-1)+')"><span class="srOnly">이전으로</span></a> ';
				}
				//페이징 10 단위로 끊기
				var i = 1;
				var forCnt = pagingCnt;
				if (pagingCnt > 10) {
					var pagingRange;
					if (parseInt(pageNo%10) == 0) {
						pagingRange = (parseInt(pageNo/10) - 1);
					} else {
						pagingRange = parseInt(pageNo/10);
					}
					i = (pagingRange*10) + 1;
					forCnt = (pagingRange+1) * 10;
					if (forCnt > pagingCnt) {
						forCnt = pagingCnt;
					}
				}
				
				for(i; i<=forCnt; i++){
					if(i == pageNo){
						inHtml += ' <a href="#" class="pg_num selected">'+i+'</a> ';
					} else {
						inHtml += ' <a href="#" class="pg_num" onclick="serch('+i+')">'+i+'</a> ';
					}
				}
				if (pageNo != pagingCnt) {
					inHtml += ' <a href="#" class="btn btn_direction next" onclick="serch('+(pageNo+1)+')"><span class="srOnly">다음으로</span></a> ';
					inHtml += ' <a href="#" class="btn btn_direction last" onclick="serch('+pagingCnt+')"><span class="srOnly">마지막으로</span></a> ';
				}
			}
		}		
	} 
 	
	return inHtml;
}
