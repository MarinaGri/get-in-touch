<#import 'macros/layout.ftl' as layout/>
<#import 'macros/form.ftl' as form/>
<#import '/spring.ftl' as spring/>

<#assign jsPaths = ['js/length_check.js']>
<@layout.main 'page.title.profile-edit' 'css/profile.css' jsPaths>
    <form method="POST" action="" enctype="multipart/form-data" class="edit-form">
        <@form.input 'form.firstName' 'form.input.first-name' 'text' 'required'/>
        <@form.input 'form.lastName' 'form.input.last-name' 'text' 'required'/>
        <@form.file 'form.input.img' 'image/*'/>
        <button type="submit" class="button"><@spring.message 'button.save'/></button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
</@layout.main>
