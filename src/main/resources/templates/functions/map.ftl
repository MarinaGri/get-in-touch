<#import '/spring.ftl' as spring/>

<#function experience>
    <#assign noExp><@spring.message code='vacancy.experience.no-experience'/></#assign>
    <#assign oneThree><@spring.message code='vacancy.experience.1-3'/></#assign>
    <#assign threeSix><@spring.message code='vacancy.experience.3-6'/></#assign>
    <#assign sixMore><@spring.message code='vacancy.experience.more-then-6'/></#assign>

    <#assign experience={
    'noExperience' : '${noExp}',
    'between1And3' : '${oneThree}',
    'between3And6' : '${threeSix}',
    'moreThan6' : '${sixMore}'
    }/>

    <#return experience>
</#function>

<#function employment>
    <#assign full><@spring.message code='vacancy.employment.full'/></#assign>
    <#assign part><@spring.message code='vacancy.employment.part'/></#assign>
    <#assign project><@spring.message code='vacancy.employment.project'/></#assign>
    <#assign volunteer><@spring.message code='vacancy.employment.volunteer'/></#assign>

    <#assign employment={
    'full' : '${full}',
    'part' : '${part}',
    'project' : '${project}',
    'volunteer' : '${volunteer}'
    }/>

    <#return employment>
</#function>

<#function schedule>
    <#assign fullDay><@spring.message code='vacancy.schedule.full-day'/></#assign>
    <#assign shift><@spring.message code='vacancy.schedule.shift'/></#assign>
    <#assign flexible><@spring.message code='vacancy.schedule.flexible'/></#assign>
    <#assign remote><@spring.message code='vacancy.schedule.remote'/></#assign>
    <#assign flyInFlyOut><@spring.message code='vacancy.schedule.fly-in-fly-out'/></#assign>

    <#assign schedule={
    'fullDay' : '${fullDay}',
    'shift' : '${shift}',
    'flexible' : '${flexible}',
    'remote' : '${remote}',
    'flyInFlyOut' : '${flyInFlyOut}'
    }/>

    <#return schedule>
</#function>

<#function gender>
    <#assign male><@spring.message code='form.radio.male'/></#assign>
    <#assign female><@spring.message code='form.radio.female'/></#assign>

    <#assign gender={
        'true' : male,
        'false' : female
    }/>

    <#return gender>
</#function>

<#function exp>
    <#assign experience><@spring.message code='form.radio.experience'/></#assign>
    <#assign noExperience><@spring.message code='form.radio.no-experience'/></#assign>

    <#assign exp={
    'true' : experience,
    'false' : noExperience
    }/>

    <#return exp>
</#function>
