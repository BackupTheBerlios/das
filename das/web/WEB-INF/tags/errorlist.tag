<%-- @author k --%>
<%@tag pageEncoding="UTF-8" body-content="empty" %>
<%@attribute name="controller" required="true" type="das.ui.ctrl.ControllerBase"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not controller.valid}">
	<div class="errorList">
		Fehler:
		<ul>
		<c:forEach items="${controller.errorList}" var="error">
			<li>${error}</li>
		</c:forEach>
		</ul>
	</div>
</c:if>
