<#import '/spring.ftl' as spring/>

<#macro static css listJs=[]>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="//cdn.ckeditor.com/4.18.0/standard/ckeditor.js"></script>

    <link rel="stylesheet" type="text/css" href="${springMacroRequestContext.contextPath}/${css}"/>
    <link rel="stylesheet" type="text/css" href="${springMacroRequestContext.contextPath}/css/main.css"/>

    <script>ctx = "${springMacroRequestContext.contextPath}"</script>
    <#if listJs?has_content>
        <#list listJs as path>
            <script src="${springMacroRequestContext.contextPath}/${path}" charset="UTF-8"></script>
        </#list>
    </#if>
</#macro>
