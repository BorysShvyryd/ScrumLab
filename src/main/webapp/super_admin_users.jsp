<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="headerLogined.jsp" %>

<div class="m-4 p-3 width-medium">
    <div class="m-4 p-3 border-dashed view-height">

        <div class="row border-bottom border-3 p-1 m-1">
            <div class="col noPadding">
                <h3 class="color-header text-uppercase">LISTA UŻYTKOWNIKÓW</h3>
            </div>
            <div class="col d-flex justify-content-end mb-2 noPadding">
                <a href="/app/dashboard" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
            </div>
        </div>

        <div class="schedules-content">
            <form action="/app/super/admin/users" method="post">
                <table class="table">
                    <thead>
                    <tr class="d-flex">
                        <th class="col-1">ID</th>
                        <th class="col-3">IMIĘ</th>
                        <th class="col-4">NAZWISKO</th>
                        <th class="col-1">STATUS</th>
                        <th class="col-3 center">AKCJE</th>
                    </tr>
                    </thead>
                    <tbody class="text-color-lighter">
                    <c:forEach var="user" items="${allUsers}">
                        <tr class="d-flex">
                            <td class="col-1">${user.getId()}</td>
                            <td class="col-3">${user.getFirstName()}</td>
                            <td class="col-4">${user.getLastName()}</td>
                            <c:choose>
                                <c:when test="${user.getEnable() == 0}">
                                    <td class="col-1">zablokowany</td>
                                </c:when>
                                <c:otherwise>
                                    <td class="col-1"></td>
                                </c:otherwise>
                            </c:choose>
                            <td class="col-3 center">
                                <button name="block" class="btn btn-danger rounded-0 text-light m-1"
                                        value="${user.getId()}">Blokuj
                                </button>
                                <button name="unblock" class="btn btn-danger rounded-0 text-light m-1"
                                        value="${user.getId()}">Оdblokuj
                                </button>
                                    <%--                            <a href="#" class="btn btn-danger rounded-0 text-light m-1">Blokuj</a>--%>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>

<%@ include file="footerLogined.jsp" %>
