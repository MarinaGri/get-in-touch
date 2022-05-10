<#import '/spring.ftl' as spring/>

<#macro account user>
    <#if user.avatarLink?has_content && user.avatarLink.link?has_content>
        <div class="round">
            <img class="avatar" src="<@spring.url '/files/' + user.avatarLink.link/>" alt="avatar">
        </div>
    <#else>
        <img class="icon" src="<@spring.url '/img/svg/person-circle.svg'/>" alt="avatar">
    </#if>

    <div class="name">
        <b>${user.firstName}<br>${user.lastName}</b>
    </div>

    <div class="follow">
        <b><@spring.message 'page.profile.resumes'/></b>
        <a href="<@spring.url '/users/' + user.id + '/resumes'/>">
            <img class="icon" src="<@spring.url '/img/svg/journals.svg'/>" alt="subscriptions">
        </a>
        <b><@spring.message 'page.profile.subs'/>:${user.numSubs}</b>
        <a href="<@spring.url '/users/' + user.id + '/subscriptions'/>">
            <img class="icon" src="<@spring.url '/img/svg/people-fill.svg'/>" alt="subscriptions">
        </a>
    </div>
</#macro>

<#macro pagination current total>
    <#assign url>${springMacroRequestContext.contextPath + '/search?'
    + springMacroRequestContext.queryString!}</#assign>
    <#assign clean>${url?replace('&page=[0-9]*', '', 'r') + '&page='}</#assign>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-end">
            <li class="page-item">
            <li class="page-item <#if current <= 0> disabled </#if>">
                <a class="page-link" href="${clean}${current - 1}">Previous</a>
            </li>
            <li class="page-item <#if (current >= total - 1)> disabled </#if>">
                <a class="page-link" href="${clean}${current + 1}">Next</a>
            </li>
        </ul>
    </nav>
</#macro>

<#macro subscribtion user profile>
    <#if user.subs?seq_contains(profile.id)>
        <#assign nextAction><@spring.message 'button.subscribe'/></#assign>
        <#assign button><@spring.message 'button.unsubscribe'/></#assign>
        <#assign nextHref><@spring.url '/subscribe/' + profile.id/></#assign>
        <#assign href><@spring.url '/unsubscribe/' + profile.id/></#assign>
    <#else>
        <#assign nextAction><@spring.message 'button.unsubscribe'/></#assign>
        <#assign button><@spring.message 'button.subscribe'/></#assign>
        <#assign nextHref><@spring.url '/unsubscribe/' + profile.id/></#assign>
        <#assign href><@spring.url '/subscribe/' + profile.id/></#assign>
    </#if>
    <input type="hidden" id="btn-link" name="${nextHref}" value="${href}">
    <input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}">
    <button id="btn" name="${nextAction}" class="button">${button}</button>
</#macro>


<#macro avatar account>
    <a href="<@spring.url '/profile/' + account.id/>">
        <#if account.avatarLink?has_content && account.avatarLink.link?has_content>
            <div class="round-small">
                <img class="avatar" src="<@spring.url '/files/' + account.avatarLink.link/>" alt="avatar">
            </div>
        <#else>
            <img src="<@spring.url '/img/svg/person-circle.svg'/>" alt="person">
        </#if>
    </a>
</#macro>

<#macro post post>
    <div class="title">${post.title!}</div>
    <div class="text">
        <#outputformat 'plainText'>
            ${post.text!''}
        </#outputformat>
    </div>
</#macro>
