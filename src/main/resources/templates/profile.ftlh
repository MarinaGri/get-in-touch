<#import 'macros/layout.ftl' as layout/>
<#import 'macros/form.ftl' as form/>
<#import 'macros/elements.ftl' as element/>
<#import '/spring.ftl' as spring/>

<#assign listJs =['js/ckeditor.js', 'js/posts.js', 'js/length_check.js', 'js/modal.js']>
<@layout.main 'page.title.profile' 'css/profile.css' listJs>
    <div class="account">
        <@element.account user/>
        <a href="<@spring.url '/profile/edit'/>">
            <button type="submit" class="button"><@spring.message 'button.edit-account'/></button>
        </a>
        <a href="<@spring.url '/logout'/>">
            <button type="submit" class="button"><@spring.message 'button.logout'/></button>
        </a>
        <button type="button" id="delete-acc"  class="button"><@spring.message 'button.delete-account'/></button>

    </div>
    <div class="posts">
        <form class="post-form" method="post">
            <input type="hidden" id="user-id" value="${user.id}">
            <@form.input 'form.title' 'form.input.title' 'text' 'required' '255'/>
            <span class="form-text" id="error"></span>
            <@form.textarea 'form.text' 'form.textarea.post'/>
            <input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}">
            <button type="button" id="btn" class="button"><@spring.message 'button.post'/></button>
        </form>
        <div id="posts">
            <#list posts as post>
                <div class="post" id="${post.id}">
                    <img class="trash" id="trash-${post.id}"
                         src="<@spring.url '/img/svg/trash.svg'/>" alt="trash">
                    <img class="edit" id="edit-${post.id}"
                         src="<@spring.url '/img/svg/pencil-square.svg'/>" alt="pencil">
                    <@element.post post/>
                </div>
            </#list>
        </div>
    </div>
</@layout.main>
