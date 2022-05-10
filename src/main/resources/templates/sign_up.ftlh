<#import 'macros/sublayouts.ftl' as layout/>
<#import 'macros/form.ftl' as form/>
<#import '/spring.ftl' as spring/>


<@layout.entrance 'page.title.sign-up'>
    <form method="POST" action="<@spring.url'/signUp'/>">
        <@form.input 'form.firstName' 'form.input.first-name' 'text' 'required'/>
        <@form.input 'form.lastName' 'form.input.last-name' 'text' 'required'/>
        <@form.input 'form.email' 'form.input.email' 'email' 'required'/>
        <@form.input 'form.password' 'form.input.password' 'password' 'required' '20'/>
        <@form.checkbox 'form.isAccepted' 'form.checkbox.agreement' 'required'/>
        <button type="submit" class="button"><@spring.message 'button.sign-up'/></button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
</@layout.entrance>
