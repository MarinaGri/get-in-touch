<#import 'macros/layout.ftl' as layout/>
<#import 'macros/form.ftl' as form/>
<#import 'macros/elements.ftl' as element/>
<#import '/spring.ftl' as spring/>


<#assign listJs =['js/users.js']>
<@layout.main 'page.title.profile' 'css/profile.css' listJs>
    <div class="account">
        <@element.account profile/>
        <@element.subscribtion user profile/>
    </div>
    <div class="posts">
        <#list posts as post>
            <div class="post" id="${post.id}">
                <@element.post post/>
            </div>
        </#list>
    </div>
</@layout.main>
