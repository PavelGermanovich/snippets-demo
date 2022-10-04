<html lang="en">
<head>
    <title>Latest</title>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<p>Hello</p>
<#list snippets as code>
    <br>
    <pre><code>${code.getCode()}</code></pre>
    <span>${code.getDate()}</span>
    <br>
</#list>
</body>
</html>