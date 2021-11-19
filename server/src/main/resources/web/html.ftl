<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=1.0">
	<title>${file.name}预览</title>
    <#include "*/commonHeader.ftl">
</head>
<#if pdfUrl?contains("http://") || pdfUrl?contains("https://")>
    <#assign finalUrl="${pdfUrl}">
<#else>
    <#assign finalUrl="${baseUrl}${pdfUrl}">
</#if>
<body>
<#if "${file.suffix?lower_case}" == "xlsx">
 <label><button onclick="tiaozhuan()">跳转其他预览方式</button></label>
 	  </#if>
    <iframe src="${finalUrl}" width="100%" frameborder="0"></iframe>
</body>
<script type="text/javascript">
    document.getElementsByTagName('iframe')[0].height = document.documentElement.clientHeight-10;
    /**
     * 页面变化调整高度
     */
    window.onresize = function(){
        var fm = document.getElementsByTagName("iframe")[0];
        fm.height = window.document.documentElement.clientHeight-10;
    }
	function tiaozhuan(){
					var test = window.location.href;
					    test = test.replace(new RegExp("&officePreviewType=html",("gm")),"");
  				    test = test+'&officePreviewType=xlsx';
			
　　     window.location.href=test;
　　}
    /*初始化水印*/
    window.onload = function() {
      initWaterMark();
	  
    }
</script>
</html>