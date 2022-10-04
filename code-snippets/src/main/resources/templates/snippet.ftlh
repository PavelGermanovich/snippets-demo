<html lang="en">
<head>
    <title>Code</title>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<br>
<pre id="code_snippet"><code>${codeDto.code}</code></pre>
<span id="load_date">${codeDto.date}</span>
<#if (codeDto.time > 0)>
    <label>Time remains</label>
<span id="time_restriction">${codeDto.time}</span>
</#if>
<#if codeDto.viewsRestrictionApplied>
<label>Views remains</label>
<span id="views_restriction">${codeDto.views}</span>
<br>
</#if>
</body>
</html>