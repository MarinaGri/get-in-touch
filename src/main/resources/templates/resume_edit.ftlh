<#import 'macros/layout.ftl' as layout/>
<#import 'macros/form.ftl' as form/>
<#import 'functions/map.ftl' as map/>
<#import '/spring.ftl' as spring/>

<#assign jsPaths = ['js/skills.js']>
<@layout.main 'page.title.resume' 'css/profile.css' jsPaths>
    <form method="POST" action="" class="form-row" id="form">
        <#if success?has_content>
            <div class="success">
                <@spring.message '${success}'/>
            </div>
        </#if>
        <div class="position">
            <@form.input 'form.position' 'form.input.position' 'text' 'required'/>
        </div>
        <div class="form-group col-md-6">
            <@form.input 'form.firstName' 'form.input.first-name' 'text' 'required'/>
        </div>
        <div class="form-group col-md-6 left">
            <@form.input 'form.lastName' 'form.input.last-name' 'text' 'required'/>
        </div>
        <div class="form-group col-md-6">
            <@form.input 'form.email' 'form.input.email' 'email' 'required'/>
        </div>
        <div class="form-group col-md-6 left">
            <@form.input 'form.mobileNumber' 'form.input.mobile-number' 'text' 'placeholder="89777777777"'/>
        </div>
        <div class="form-group col-md-6">
            <@form.input 'form.currentCity' 'form.input.current-city' 'text'/>
        </div>
        <div class="form-group col-md-6 left">
            <@form.input 'form.dateOfBirth' 'form.input.date-of-birth' 'date' 'required'/>
        </div>
        <div class="form-group col-md-6">
            <@form.radio 'form.gender' map.gender()/>
        </div>
        <div class="form-group col-md-6">
            <@form.radio 'form.experience' map.exp()/>
        </div>
        <div class="skill"><@spring.message 'page.resume.skills'/></div>
        <img class="edit" id="btn-delete-skill"
             src="<@spring.url '/img/svg/dash-circle.svg'/>" alt="dash">
        <img class="edit" id="btn-add-skill"
             src="<@spring.url '/img/svg/plus-circle.svg'/>" alt="plus">
        <div id="skills" class="form-group">
            <div class="form-group col-md-2">
                <input type="text" name="skills[0]" class="form-control"/>
            </div>
        </div>
        <button type="submit" class="btn"><@spring.message 'button.save'/></button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
</@layout.main>
