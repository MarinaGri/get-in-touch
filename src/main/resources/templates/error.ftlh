<#import '/spring.ftl' as spring/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<@spring.url '/css/error.css'/>"/>
    <title>Error</title>
</head>
<body>
<div class="code">
    <#if status?has_content>
        ${status}
    <#else>
        500
    </#if>
</div>
<div class="message">
    <#if error?has_content>
        ${error}
    <#else>
        Something went wrong...
    </#if>
</div>
</body>
</html>
