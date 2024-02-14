<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>" scope="application"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <meta charset="utf-8" />
    <title>테스트 작업</title>
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
    />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" /
    <link rel="stylesheet" type="text/css" media="screen" href="../resources/css/jquery-ui-1.10.4.custom.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="../resources/css/ui.jqgrid.css" />
    // jQuery js파일을 jqGrid js파일보다 상위에 선언해야 제대로 동작함
    <script src="../resources/js/jquery-1.11.0.min.js" type="text/javascript"></script>
    <script src="../resources/js/i18n/grid.locale-kr.js" type="text/javascript"></script>
    <script src="../resources/js/jquery.jqGrid.min.js" type="text/javascript"></script>

</head>


<script language=javascript>
    function currencyFormatter(amount){
        return amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g,',');
    }
    //날자 포맷
    function dateFormatter(stringDate){
        if(!stringDate){
            return "";
        }
        var formatNum = '';
        stringDate=stringDate.replace(/\s/gi, "");
        if(stringDate.length == 8){
            formatNum = stringDate.replace(/(\d{4})(\d{2})(\d{2})/, '$1.$2.$3');
        }else{
            formatNum = stringDate;
        }
        // document.getElementById(id).innerHTML = formatNum;
        //console.log("dateFormatter formatNum -->"+formatNum+" ..... num ->"+num);
        return formatNum;
    }

    //전화번호 포맷
    function phoneFormatter(num) {
        var formatNum = '';
        try{
            num = num.replace(/[^0-9]/g, "");
            if (num.length == 11) {
                formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
            } else if (num.length == 8) {
                formatNum = num.replace(/(\d{4})(\d{4})/, '$1-$2');
            } else {
                if (num.indexOf('02') == 0) {
                    formatNum = num.replace(/(\d{2})(\d{4})(\d{4})/, '$1-$2-$3');
                } else {
                    formatNum = num.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
                }
            }
        } catch(e) {
            formatNum = num;
        }
        console.log("phoneFormatter formatNum -->"+formatNum+" ..... num ->"+num);
        return formatNum;
    }
    //사업자등록번호 포맷
    function bizNoFormatter(num, type) {
        var formatNum = '';
        try{
            if (num.length == 10) {
                if (type == 0) {
                    formatNum = num.replace(/(\d{3})(\d{2})(\d{5})/, '$1-$2-*****');
                } else {
                    formatNum = num.replace(/(\d{3})(\d{2})(\d{5})/, '$1-$2-$3');
                }
            }
        } catch(e) {
            formatNum = num;
        }
        console.log("bizNoFormatter formatNum -->"+formatNum+" ..... num ->"+num);
        return formatNum;
    }
</script>
<body>
    <!-- jqGrid 페이징 처리 예시 -->
    <div class="grid full-height full-height-strict">
        <table id="grid" class="full-size-jq-grid"></table>
     </div>
     <div id="pager" style="height: 35px; "></div></div>
    <!-- // header -->
// FORM 태그로 감싼 모든 Submit 데이터


var formData = $('#FORM1').serialize();

// 기존 그리드를 담은 엘리먼트(테이블) 제거
$('#grid').jqGrid("clearGridData")
$.jgrid.gridDestroy("grid")
// 새 테이블(그리드를 담을 그릇) 추가
// id = parentDiv 인 <div> 는 기존에 grid 를 적용한 테이블을 감싸고 있는 바로 상위의 엘리먼트
$("#parentDiv").append('<table id="grid" class="full-size-jq-grid"></table>')


// grid 란 id 값을 가진 테이블에 jqGrid 생성
$("#grid").jqGrid({
	url : "<%=APP_ROOT%>/auth/root/bbs/getList",
	        postData : formData,
	        datatype: "json",
	        /*
	              // colModel 옵션들
	              name : 출력할 데이터의 이름입니다. 서버에서 받은 데이터의 변수명과 일치해야 함.
	              index : 컬럼 정렬 시 서버에 넘어가는 값이다. index값을 설정하지 않으면 name값이 넘어간다.
	              width : 컬럼의 넓이를 설정.
	              align : 컬럼 내 데이터의 정렬을 설정.
	              hidden : 데이터값은 설정하고 화면에서 보이고 싶지 않을 때 사용.
	              formatter : 데이터로 들어온 값을 특정 형식으로 변환해서 보여줄 수 있다.
	              frozen : true or false // 이 옵션을 넣은경우 그리드를 전부다 그린뒤
	              $("#gridid").jqGrid('setFrozenColumns'); 함수를 호출해줘야한다. + reload 까지도
	        */
	        colModel : [
	             {label : "KEY",name : "ID", width:120, hidden: true},
	             {label : "ID", name : "ID", align:'center'},
	             {label : "나이", name : "AGE", align:'center', formatter : "integer", sorttype : "integer"},
	             {label : "이름", name : "NAMES", align:'center', editable:true, edittype:'text'
	                     , editoptions:{
	                     }
	             },
	        ],
	        formatter: {
	        		integer: {thousandsSeparator: ",", defaultValue: '0'}
	        },
	        cellEdit: true,
	        cellsubmit: 'clientArray',
	        cellurl : '',
	        afterEditCell:function(rowid, cellname, value, iRow, iCol){
	             /*
	              //input HTML 태그가 입력되어버리는 문제 해결
	              //jqGrid에서 CellEdit 모드 일 때 마우스가 Input에서 focus를 벗어 났을 때 Cell Save
	              $("#"+rowid+"_"+cellname).blur(function(){
	                   $("#grid").jqGrid("saveCell",iRow,iCol);
	              });
	             */
	         },
	         loadtext : '로딩중..',
	         autowidth: true,
	         loadonce : true,
	         viewrecords: true,
	         height : 'auto',
	         rowNum: 20,
	         rowList:[10,30,50,80,100],
	         pager: '#pager',
	         pgtext : "Page {0} of {1}",
	         jsonReader: {cell:""},
	         multiselect:true,
	         ondblClickRow: function(id,irow,icol,e){

	         },
	         onSelectRow  : function(rowid){
	             var idArry = $("#grid").jqGrid('getDataIDs'); //grid의 id 값을 배열로 가져옴

	             for(var i=0 ; i < idArry.length; i++){
	                 var ret =  $("#grid").getRowData(idArry[i]); // 해당 id의 row 데이터를 가져옴
	                 if("N" != ret.finish_yn){ //해당 row의 COMPLETED_YN 컬럼 값이 N이 아니면 checkbox disabled 처리
	                    //해당 row의 checkbox disabled 처리 "jqg_list_" 이 부분은 grid에서 자동 생성
	                    $("#jqg_grid_"+idArry[i]).removeAttr("checked");
	                    $("#"+idArry[i]).removeClass('ui-state-highlight');
	                 }
	             }
	         },

	         onSelectAll: function(aRowids,status) { //disabled 처리된 checkbox 선택 안되도록 해주는 부분
	             if (status) {
	                 var cbs = $("tr.jqgrow > td > input.cbox:disabled", $("#grid")[0]);
	                 cbs.removeAttr("checked");
	                 $("#grid")[0].p.selarrrow = $("#grid").find("tr.jqgrow:has(td > input.cbox:checked)").map(function() { return this.id; }).get();

	                 var idArry = $("#grid").jqGrid('getDataIDs');
	                 for(var i=0 ; i < idArry.length; i++){
	                     var ret =  $("#grid").getRowData(idArry[i]);
	                     //if("N" != ret.COMPLETED_YN){
	                     if("N" != ret.finish_yn){ //해당 row의 COMPLETED_YN 컬럼 값이 N이 아니면 checkbox disabled 처리

	                        $("#"+idArry[i]).removeClass('ui-state-highlight');
	                     }
	                 }
	             }
	        },
	        loadComplete: function (data) {
	             var allRow = jQuery("#grid").jqGrid('getGridParam', 'records');
	             if(allRow == 0 ){
	                $("#grid >tbody").append("<tr><td align='center' colspan='10' style=''>조회된 데이터가 없습니다.</td></tr>");
	             }

	             var idArry = $("#grid").jqGrid('getDataIDs');

	             for(var i=0 ; i < idArry.length; i++){
	                 var ret =  $("#grid").getRowData(idArry[i]);
	                 //if("N" != ret.COMPLETED_YN){
	                 if("N" != ret.finish_yn){ //해당 row의 COMPLETED_YN 컬럼 값이 N이 아니면 checkbox disabled 처리
	                     $("#jqg_grid_"+idArry[i]).attr("disabled", true);
	                 }
	             }
	        },
})


<!--
url : 데이터 API 요청을 보낼 주소를 입력
mtype : API 요청 방식을 설정(get || post)
datatype : 가지고 오는 데이터의 타입을 설정한다. 보통 xml, json,local 이렇게 세 가지를 자주 사용.
colNames : 그리드 각각의 컬럼에 출력되는 이름이고, 배열로 설정한다.
colModel : 각 컬럼에 대한 상세 정보이다. 서버로부터 받아온 데이터를 매핑해서 출력한다.
 - colModel 하위에 label이 들어가는데, colNames를 사용하지 않고 label 내부에 설정해도 동일하게 사용이 가능하다.(하단 코드 예시 참고)
jsonReader/xmlReader : 데이터 타입이 json/xml일 경우 reader를 통해서 데이터를 어떻게 읽어들일지 설정.
rowNum : 초기에 출력할 데이터의 개수를 설정.
pager : 그리드의 대표기능인 페이저를 설정, jqGrid <Table> 하위에 <div> 를 넣어주고 그 div의 id값을 써주면 된다.(하단 예시 참고)
multiselect : row마다 selectbox가 생긴다(이벤트 처리 가능)postData : 서버에 파라미터로 넘길 데이터를 설정한다. 배열의 형태로 설정 가능하고, <FORM> 태그로 감싼 데이터들을 한번에 serialize() 해서 보낼수도 있다.
loadComplete : 서버에 모든 요청 후 즉시 발생
onCellSelect : 그리드의 특정 셀을 클릭시 발생
ondblClickRow : row가 더블클릭한 직후 발생
autowidth: true,    // jQgrid width 자동100% 채워지게
shrinkToFit: false,  // width를 자동설정 해주는 기능
-->