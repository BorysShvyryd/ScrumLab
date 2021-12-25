<%--
  Created by IntelliJ IDEA.
  User: zbigniew
  Date: 23.07.2021
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/header.jsp" %>

<section class="dashboard-section">
    <div class="container pt-4 pb-4">
        <div class="border-dashed view-height">
            <div class="container w-25">
                <!-- fix action, method -->
                <!-- add name attribute for all inputs -->
                <form class="padding-small text-center" method="post" action="/register">
                    <h1 class="text-color-darker">Rejestracja</h1>
                    <div class="form-group">
                        <input type="text" class="form-control" id="name" name="name" placeholder="podaj imię" value="${name}">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="surname" name="surname" placeholder="podaj nazwisko" value="${surname}">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="email" name="email" placeholder="podaj email" value="${email}">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="password" name="password" placeholder="podaj hasło">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="repassword" name="password" placeholder="powtórz hasło">
                    </div>
                    <button class="btn btn-color rounded-0" type="submit">Wyślij</button>
                </form>
            </div>
        </div>
    </div>
</section>
<%@ include file="/footer.jsp" %>
<%--

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>--%>
