<#import 'macros/layout.ftl' as layout/>
<#import 'macros/form.ftl' as form/>
<#import 'macros/elements.ftl' as element/>
<#import '/spring.ftl' as spring/>

<#assign listJs =['js/resumes.js']>
<@layout.main 'page.title.resumes' 'css/profile.css' listJs>
    <a href="<@spring.url '/resumes/edit'/>">
        <button id="right-btn" class="button"><@spring.message 'button.add-resume'/></button>
    </a>
    <#if resumes?has_content>
        <div class="resumes">
            <#list resumes as resume>
                <div class="post" id="${resume.id}">
                    <#if id == resume.userId>
                        <img class="trash" id="trash-${resume.id}"
                             src="<@spring.url '/img/svg/trash.svg'/>" alt="trash">
                    </#if>
                    <div class="title">${resume.position}</div>
                    <b>
                        <span class="name">${resume.firstName} ${resume.lastName}</span>
                    </b><br>
                    <b><@spring.message 'form.input.current-city'/>: </b>${(resume.currentCity)!}<br>
                    <b><@spring.message 'form.input.mobile-number'/>: </b>${(resume.mobileNumber)!}<br>
                    <b><@spring.message 'form.input.email'/>: </b>${(resume.email)!}<br>
                    <b><@spring.message 'page.resume.experience'/>: </b>
                    <#if resume.experience?has_content>
                        <#if resume.experience>
                            <@spring.message 'form.radio.experience'/>
                        <#else>
                            <@spring.message 'form.radio.no-experience'/>
                        </#if>
                    </#if><br>
                    <b><@spring.message 'page.resume.gender'/>:</b>
                    <#if resume.gender?has_content>
                        <#if resume.gender>
                            <@spring.message 'form.radio.male'/>
                        <#else>
                            <@spring.message 'form.radio.female'/>
                        </#if>
                    </#if><br>
                    <b><@spring.message 'page.resume.skills'/>:</b>
                    <#list resume.skills as skill>
                        ${skill + ' '}
                    </#list><br>
                </div>
            </#list>
        </div>
    </#if>
    <input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}">
</@layout.main>
