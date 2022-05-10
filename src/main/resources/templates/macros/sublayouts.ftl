<#import 'layout.ftl' as layout/>
<#import '/spring.ftl' as spring/>

<#macro entrance title>
    <#assign jsPaths = ['js/length_check.js']>
    <@layout.main title 'css/entrance.css', jsPaths>
        <div class="main-row">
            <div class="form-col">
                <#nested>
            </div>
            <div class="ladder-col">
                <div class="picture">
                    <img src="<@spring.url '/img/people.jpg'/>" alt="People">
                </div>
                <div class="step-3">
                    <@spring.message 'page.entrance.step-1'/>
                </div>
                <div class="step-2">
                    <@spring.message 'page.entrance.step-2'/>
                </div>
                <div class="step-1">
                    <@spring.message 'page.entrance.step-3'/>
                </div>
            </div>
        </div>
    </@layout.main>
</#macro>
