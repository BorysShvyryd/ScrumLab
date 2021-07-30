<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="headerLogined.jsp" %>

<div class="m-4 p-3 width-medium ">
    <div class="dashboard-content border-dashed p-3 m-4">
        <div class="row border-bottom border-3 p-1 m-1">
            <div class="col noPadding">
                <h3 class="color-header text-uppercase">SZCZEGÓŁY PLANU</h3>
            </div>
            <div class="col d-flex justify-content-end mb-2 noPadding">
                <a href="/app/plan/list" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
            </div>
        </div>

        <div class="schedules-content">
            <div class="schedules-content-header">
                <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Nazwa planu
                                </span>
                    <div class="col-sm-10">
                        <p class="schedules-text">${plan.getName()}</p>
                    </div>
                </div>
                <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Opis planu
                                </span>
                    <div class="col-sm-10">
                        <p class="schedules-text">
                            ${plan.getDescription()}
                        </p>
                    </div>
                </div>
            </div>

            <c:set var="day" value=""/>
            <c:forEach var="details" items="${listDetailsPlan}">
                <table class="table">
                    <c:if test="${not day.equals(details[0])}">
                        <thead>
                        <tr class="d-flex">
                            <th class="col-2">${details[0]}</th>
                            <th class="col-7"></th>
                            <th class="col-1"></th>
                            <th class="col-2"></th>
                        </tr>
                        </thead>
                        <c:set var="day" value="${details[0]}"/>
                    </c:if>
                        <tbody class="text-color-lighter">
                        <tr class="d-flex">
                            <td class="col-2">${details[1]}</td>
                            <td class="col-7">${details[2]}</td>
                            <td class="col-1 center">
                                <a href="/app/recipe/plan/delete?plan_id=${plan.getId()}&id=${details[4]}" class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                            </td>
                            <td class="col-2 center">
                                <a href="/app/recipe/details?id=${details[3]}"
                                   class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                            </td>
                        </tr>
                        </tbody>
                </table>
            </c:forEach>
        </div>
    </div>
</div>

<%@ include file="footerLogined.jsp" %>
