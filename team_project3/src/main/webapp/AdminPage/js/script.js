$('#toggle-left-menu').click(function() {
        if ($('#left-menu').hasClass('small-left-menu')) {
            $('#left-menu').removeClass('small-left-menu');
        } else {
            $('#left-menu').addClass('small-left-menu');
        }
        $('#logo').toggleClass('small-left-menu');
        $('#page-container').toggleClass('small-left-menu');
        $('#header .header-left').toggleClass('small-left-menu');

        $('#logo .big-logo').toggle('300');
        $('#logo .small-logo').toggle('300');
        $('#logo').toggleClass('p-0 pl-1');
    });

    $(document).on('mouseover', '#left-menu.small-left-menu > ul > li', function() {
        if (!$(this).hasClass('has-sub')) {
            var label = $(this).find('span').text();
            var position = $(this).position();
            $('#show-lable').css({
                'top': position.top + 79,
                'left': position.left + 59,
                'opacity': 1,
                'visibility': 'visible'
            });

            $('#show-lable').text(label);
        } else {
            var position = $(this).position();
            $(this).find('ul').addClass('open');

            if ($(this).find('ul').hasClass('open')) {
                const height = 47;
                var count_submenu_li = $(this).find('ul > li').length;
                if (position.top >= 580) {
                    var style = {
                        'top': (position.top + 100) - (height * count_submenu_li),
                        'height': height * count_submenu_li + 'px'
                    }
                    $(this).find('ul.open').css(style);
                } else {
                    var style = {
                        'top': position.top + 79,
                        'height': height * count_submenu_li + 'px'
                    }

                    $(this).find('ul.open').css(style);
                }

            }
        }

    });

    $(document).on('mouseout', '#left-menu.small-left-menu li a', function(e) {
        $('#show-lable').css({
            'opacity': 0,
            'visibility': 'hidden'
        });
    });

    $(document).on('mouseout', '#left-menu.small-left-menu li.has-sub', function(e) {
        $(this).find('ul').css({
            'height': 0,
        });
    });

    $(window).resize(function() {
        windowResize();
    });

    $(window).on('load', function() {
        windowResize();
    });

    $('#left-menu li.has-sub > a').click(function() {
        var _this = $(this).parent();

        _this.find('ul').toggleClass('open');
        $(this).closest('li').toggleClass('rotate');

        _this.closest('#left-menu').find('.open').not(_this.find('ul')).removeClass('open');
        _this.closest('#left-menu').find('.rotate').not($(this).closest('li')).removeClass('rotate');
        _this.closest('#left-menu').find('ul').css('height', 0);

        if (_this.find('ul').hasClass('open')) {
            const height = 47;
            var count_submenu_li = _this.find('ul > li').length;
            _this.find('ul').css('height', height * count_submenu_li + 'px');
        }
    });


    function windowResize() {
        var width = $(window).width();
        if (width <= 992) {
            $('#left-menu').addClass('small-left-menu');
            $('#logo').addClass('small-left-menu p-0 pl-1');
        } else {
            $('#left-menu').removeClass('small-left-menu');
            $('#logo').removeClass('small-left-menu p-0 pl-1');
        }
    }
    
    
    //관리자 페이지에서 쓸 로그아웃(추후 회원가입/로그인 파트에서 구현한 함수 호출 할 수도 있음.)
    	function confirmLogout() {
			//alert("함수확인");
			if(confirm("로그아웃 하시겠습니까?")) {
					location.href = "./Logout.ma";
				}
	}
	
	
	//qna 삭제 확인하기 (코드 수정 : 같은 구문으로 합치는 방법 확인하기)
		function confirmDelete(){
			let qna_num = document.getElementById("qna_num").value;
			let page = document.getElementById("page").value;
			
			if(document.getElementById("qna_num")){
				if(confirm("삭제 하시겠습니까?")) {
				location.href = "./QnaDeletePro.co?qna_num=" + qna_num+"&page="+page;
				
			}
			
		}
	}


	
	//공지사항 삭제 확인하기
		function noticeDelete(){
			let notice_num = document.getElementById("notice_num").value;
			let page = document.getElementById("page").value;
			
			if(document.getElementById("notice_num")){
				if(confirm("공지사항 게시물을 삭제 하시겠습니까?")) {
				location.href = "./NoticeDelete.co?notice_num=" + notice_num+"&page="+page;
				
			}
			
		}
	}

	//이벤트 삭제 확인하기
		function eventDelete(){
			let event_num = document.getElementById("event_num").value;
			let page = document.getElementById("page").value;
			
			if(document.getElementById("event_num")){
				if(confirm("이벤트 게시물을 삭제 하시겠습니까?")) {
				location.href = "./EventDelete.co?event_num=" + event_num+"&page="+page;
				
			}
			
		}
	}



	//검수디테일에서 검수상태변경 파라미터로 보내기
		function confirmStatus(status,sell_num,sNickname) {
			let cmStatus = status;
			let sellNum = sell_num;
			let sNname = sNickname;
			
			location.href = "./UpdateProductConfirm.co?cmStatus=" + cmStatus+"&sell_num="+sellNum+"&sNname="+sNname;
		}



	//검수상태에 따른 목록 
		
		function confirmType(clicked_id){
			let cmStatus=clicked_id;
			if(cmStatus=="progress"){
				cmStatus ="검수중";
			} else if(cmStatus=="cancel"){
				cmStatus ="검수반려";
			} else if(cmStatus=="sale"){
				cmStatus ="판매완료";
			} else {
				cmStatus ="판매중";
			}
				location.href = "./ProductConfirmType.co?cmStatus=" + cmStatus;
		}
		
		
		
		
		
		
		
		
		
