<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/headerLogined.jsp" %>

<div class="m-4 p-4 width-medium">
    <div class="dashboard-header m-4">
        <div class="dashboard-menu">
            <div class="menu-item border-dashed">
                <a href="/app/recipe/add">
                    <i class="far fa-plus-square icon-plus-square"></i>
                    <span class="title">dodaj przepis</span>
                </a>
            </div>
            <div class="menu-item border-dashed">
                <a href="/app/plan/add">
                    <i class="far fa-plus-square icon-plus-square"></i>
                    <span class="title">dodaj plan</span>
                </a>
            </div>
            <div class="menu-item border-dashed">
                <a href="/app/recipe/plan/add">
                    <i class="far fa-plus-square icon-plus-square"></i>
                    <span class="title">dodaj przepis do planu</span>
                </a>
            </div>
        </div>

        <div class="dashboard-alerts">
            <div class="alert-item alert-info">
                <i class="fas icon-circle fa-info-circle"></i>
                <span class="font-weight-bold">Liczba przepisów: ${numberRecipe}</span>
            </div>
            <div class="alert-item alert-light">
                <i class="far icon-calendar fa-calendar-alt"></i>
                <span class="font-weight-bold">Liczba planów: ${numberPlan}</span>
            </div>
        </div>
    </div>
    <div class="m-4 p-4 border-dashed">
        <h2 class="dashboard-content-title">
            <span>Ostatnio dodany plan:</span> ${lastPlan.getName()}
        </h2>
        <table class="table">
            <c:set var="varDay" value=""/>
            <c:forEach var="day" items="${lastPlanDetails}" varStatus="index">
                <c:if test="${not varDay.equals(day[1])}">
                    <thead>
                    <tr class="d-flex">
                        <th class="col-2">${day.get(1)}</th>
                        <th class="col-8"></th>
                        <th class="col-2"></th>
                    </tr>
                    </thead>
                    <c:set var="varDay" value="${day[1]}"/>
                </c:if>
                <tbody>
                <tr class="d-flex">
                    <td class="col-2">${day.get(2)}</td>
                    <td class="col-8">${day.get(3)}</td>
                    <td class="col-2">
                        <a href="/app/recipe/details?id=${day.get(5)}"
                           class="btn btn-primary rounded-0">Szczegóły</a>
                    </td>
                </tr>

                </tbody>
            </c:forEach>
        </table>
    </div>
</div>

<%@ include file="/footerLogined.jsp" %>