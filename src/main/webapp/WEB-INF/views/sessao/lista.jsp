<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="ingresso" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ingresso:template>
    <jsp:body>
		<div class=" col-md-6 col-md-offset-3">
        <h3>Sessões na sala: ${sala.nome}</h3>

        <table class="table table-hover">
            <caption>Sessões na sala: ${sala.nome}</caption>
            <thead>
            <tr>
                <th scope="col">Horário</th>
                <th scope="col">Filme</th>
                <th scope="col">Duração</th>
                <th scope="col">Preço</th>

                <th scope="col" colspan="2" class="text-center">Ações</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="sessao" items="${sessoes}">
                <tr>
                    <td>${sessao.horario}</td>
                    <td>${sessao.filme.nome}</td>
                    <td>${sessao.filme.duracao.toMinutes()}</td>
                    <td>${sessao.preco}</td>

                    <td>
                        <a onclick="excluir(${sessao.id})" class="btn btn-danger">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="col-md-6 col-md-offset-3">
            <a href="${pageContext.request.contextPath}/admin/sessao?salaId=${sala.id}" class="btn btn-block btn-info">Nova</a>
        </div>
		</div>
        <script>
            function excluir(id) {
                const url = window.location.href;
                $.ajax({
                    url:"/admin/sessao/" + id,
                    type: 'DELETE',
                    success: function (result) {
                        console.log(result);

                        window.location.href = url;
                    }
                });
            }
        </script>
    </jsp:body>
</ingresso:template>
