<#import 'macros/sublayouts.ftl' as layout/>
<#import 'macros/form.ftl' as form/>
<#import '/spring.ftl' as spring/>

<@layout.entrance "page.title.sign-in">
    <form method="POST" action="" class="form-row">
        <@form.input 'form.email' 'form.input.email' 'email' 'required'/>
        <@form.input 'form.password' 'form.input.password' 'password' 'required'/>
        <#if error>
            <span class="form-text"><@spring.message 'error.sign-in'/></span>
        </#if>
        <@form.checkbox 'form.rememberMe' 'form.checkbox.remember-me'/>
        <button type="submit" class="button" name="sent"><@spring.message 'button.sign-in'/></button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
    <a href="<@spring.url'/signUp'/>">
        <button class="button"><@spring.message 'button.sign-up'/></button>
    </a>
    <a href="https://oauth.vk.com/authorize?client_id=8163083&redirect_uri=http://localhost/sem/oauth&response_type=code&scope=email&v=5.131">
        <button class="button"><@spring.message 'button.sign-in-with'/> VK</button>
    </a>
</@layout.entrance>
