<#import '/spring.ftl' as spring/>

<#macro input path code type='text' required='' length=''>
    <@spring.bind path/>
    <div class="mb-3">
        <label class="form-label"><@spring.message code/></label>
        <#if (length?has_content)>
            <div class="count" id="${path}">0/${length}</div>
        </#if>
        <@spring.formInput path 'class="form-control" ${required}' type/>
        <@spring.showErrors ' ' 'form-text'/>
    </div>
</#macro>

<#macro checkbox path code required='' checked=''>
    <div class="mb-3 form-check">
    <@spring.formCheckbox path '${required} ${checked} type="checkbox" class="form-check-input" id="exampleCheck1"'/>
        <label class="form-check-label" for="exampleCheck1"><@spring.message code/></label>
    </div>
</#macro>

<#macro textarea path code>
    <div class="mb-3">
        <label class="form-label"><b><@spring.message code/></b></label>
        <@spring.formTextarea path 'class="form-control" rows="2"'/>
    </div>
</#macro>

<#macro select path map>
    <@spring.formSingleSelect path map 'class="form-select" aria-label=".form-select-lg example"'/>
    <br>
</#macro>

<#macro file code accept='*'>
    <div class="mb-3">
        <label for="formFile" class="form-label"><@spring.message code/></label>
        <input class="form-control" type="file" id="formFile" name="file" accept="${accept}">
    </div>
</#macro>

<#macro radio path options>
    <@spring.formRadioButtons path options ' ' 'class="form-check-input"'/>
</#macro>
