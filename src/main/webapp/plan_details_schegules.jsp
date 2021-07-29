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

            <%--            <c:out value="${day}" default=""/>--%>
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
                        <%--                        <c:out value="${day}" default="${details[0]}"/>--%>
                    </c:if>
                        <%--                    <c:forEach var="detail" items="details">--%>

                        <tbody class="text-color-lighter">
                        <tr class="d-flex">
                        <c:forEach begin="1" end="3" var="det">
                            <td class="col-2">${det}</td>
                            <td class="col-7">płatki owsiane z jagodami i komosą ryżową</td>
                            <td class="col-1 center">
                                <a href="#" class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                            </td>
                            <td class="col-2 center">
                                <a href="app-details-schedules.html"
                                   class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                            </td>
                        </c:forEach>
                        </tr>
                        </tbody>
                        <%--                    </c:forEach>--%>
                </table>
            </c:forEach>
        </div>
    </div>
</div>

<%@ include file="footerLogined.jsp" %>
