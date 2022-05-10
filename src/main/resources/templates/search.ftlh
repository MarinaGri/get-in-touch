<#import 'macros/layout.ftl' as layout/>
<#import 'macros/form.ftl' as form/>
<#import 'macros/elements.ftl' as element/>
<#import 'functions/map.ftl' as map/>
<#import '/spring.ftl' as spring/>


<#assign listJs =['js/comments.js']>
<@layout.main 'page.title.search' "css/search.css" listJs>
    <#assign url >https://hh.ru/vacancy/</#assign>
    <div class="feed-col">
        <#list page.items! as vacancy>
            <div class="vacancy-block">
                <a href="${url + vacancy.id}">
                    <h3>
                        <b>${vacancy.name}</b>
                    </h3>
                </a>
                <div class="param">
                    <b><@spring.message 'vacancy.attr.area'/>:</b> ${(vacancy.area.name)!}<br>
                    <b><@spring.message 'vacancy.attr.schedule'/>:</b> ${(vacancy.schedule.name)!}<br>
                    <b><@spring.message 'vacancy.attr.salary'/>:</b> ${(vacancy.salary.from)!}-${(vacancy.salary.to)!}<br>
                    <b><@spring.message 'vacancy.attr.requirements'/>:</b> ${(vacancy.snippet.requirement)!}<br>
                    <b><@spring.message 'vacancy.attr.responsibilities'/>:</b> ${(vacancy.snippet.responsibility)!}<br>
                </div>
                <img class="chat" id="chat-${vacancy.id}" data-toggle="tooltip" data-placement="top"
                     title="<@spring.message 'tooltip.comments'/>"
                     src="<@spring.url '/img/svg/chat-text.svg'/>" alt="pencil">
                <input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}">
                <input type="hidden" id="user-id" value="${id}">
                <div class="comments" id="${vacancy.id}">
                </div>
            </div>
        </#list>
    </div>
    <div class="form-col">
        <form method="get" action="">
            <@form.input 'vacancyForm.text' 'form.input.key-word' 'text'/>
            <@form.input 'vacancyForm.salary' 'form.input.salary' 'text'/>
            <@form.select 'vacancyForm.experience' map.experience() />
            <@form.select 'vacancyForm.employment' map.employment() />
            <@form.select 'vacancyForm.schedule' map.schedule() />
            <@form.checkbox 'vacancyForm.onlyWithSalary' 'form.checkbox.salary'/>
            <button type="submit" class="button"><@spring.message 'button.search'/></button>
        </form>
    </div>
    <@element.pagination page.page!0 page.pages!0/>
</@layout.main>
