<#import 'macros/layout.ftl' as layout/>
<#import 'macros/form.ftl' as form/>
<#import 'macros/elements.ftl' as element/>
<#import '/spring.ftl' as spring/>

<@layout.main 'page.title.subscriptions' 'css/profile.css'>
    <#if accounts?has_content>
        <div class="subs">
            <table class="table">
                <tr>
                    <th><@spring.message 'page.subs.link'/></th>
                    <th><@spring.message 'page.subs.first-name'/></th>
                    <th><@spring.message 'page.subs.last-name'/></th>
                </tr>
                <#list accounts as account>
                    <tr>
                        <td>
                            <@element.avatar account/>
                        </td>
                        <td>${account.firstName}</td>
                        <td>${account.lastName}</td>
                    </tr>
                </#list>
            </table>
        </div>
    </#if>
</@layout.main>
