<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="headerLogined.jsp" %>

<div class="m-4 p-3 width-medium">
    <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <form action="/app/plan/delete" method="post">
            <div class="row border-bottom border-3 p-1 m-1">
                <div class="col noPadding">
                    <h3 class="color-header text-uppercase">Czy na pewno chcesz usunąć przepis z planu?</h3>
                </div>

            </div>
            <div class="col d-flex justify-content-end mb-2 noPadding">
                <button type="submit" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4" name="plan_id" value="${id}">OK</button>
                <a href="/app/plan/list" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Cancel</a>
            </div>
        </form>
    </div>
</div>