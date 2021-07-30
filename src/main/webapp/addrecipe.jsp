<%--
  Created by IntelliJ IDEA.
  User: zbigniew
  Date: 23.07.2021
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/header.jsp" %>

<section class="dashboard-section">
    <div class="container pt-4 pb-4">
        <div class="border-dashed view-height">
            <div class="container w-25">
                <!-- fix action, method -->
                <!-- add name attribute for all inputs -->
                <form class="padding-small text-center" method="post" action="/app/recipe/add">
                    <h1 class="text-color-darker">Dodaj nowy przepis</h1>
                    <div class="form-group">
                        <input type="text" class="form-control" id="name" name="name" placeholder="nazwa przepisu">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="description" name="description" placeholder="opis przepisu">
                    </div>
                    <div class="form-group">
                        <input type="number" class="form-control" id="timePreparation" name="timePreparation" placeholder="czas przygotowania w minutach">
                    </div>
                    <div class="form-group">
                        <input type="textarea" class="form-control" id="typeOf" name="typeOf" placeholder="sposób przygotowania">
                    </div>
                    <div class="form-group">
                        <input type="textarea" class="form-control" id="ingredients" name="ingredients" placeholder="składniki">
                    </div>
                    <button class="btn btn-color rounded-0" type="submit">Wyślij</button>
                </form>
            </div>
        </div>
    </div>
</section>
<%@ include file="/footer.jsp" %>
