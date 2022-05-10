<#import 'macros/layout.ftl' as layout/>
<#import 'macros/form.ftl' as form/>
<#import 'macros/elements.ftl' as element/>
<#import '/spring.ftl' as spring/>

<#assign listJs = []>
<@layout.main 'page.title.profile' 'css/profile.css' listJs>
    <div class="filter-group">
        <a href="<@spring.url '/posts/search/subscribers'/>" class="limit">
            <img class="icon" src="<@spring.url '/img/svg/person-hearts.svg'/>" alt="profile">
        </a>
        <a href="<@spring.url '/posts/search/activities'/>" class="limit">
            <img class="icon" src="<@spring.url '/img/svg/pen.svg'/>" alt="profile">
        </a>
        <a href="<@spring.url '/posts/search/skills'/>">
            <img class="icon" src="<@spring.url '/img/svg/lightning-charge.svg'/>" alt="feed">
        </a>
    </div>
    <div class="form-col">
        <form method="get" action="<@spring.url '/posts/search'/>">
            <@form.input 'filter.title' 'form.input.title' 'text'/>
            <@form.input 'filter.text' 'form.input.text' 'text'/>
            <@form.input 'filter.firstName' 'form.input.first-name' 'text'/>
            <@form.input 'filter.lastName' 'form.input.last-name' 'text'/>
            <button type="submit" class="button"><@spring.message 'button.search'/></button>
        </form>
    </div>
    <div class="resumes">
        <#if title?has_content>
            <div class="top-title"><h2><@spring.message title/></h2></div>
        </#if>
        <#list posts as post>
            <div class="post" id="${post.id}">
                <@element.avatar post.user/>
                ${post.user.firstName + '  ' + post.user.lastName}
                <@element.post post/>
            </div>
        </#list>
    </div>
</@layout.main>
