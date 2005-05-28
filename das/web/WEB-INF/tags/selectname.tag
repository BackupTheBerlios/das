<%@tag pageEncoding="UTF-8" body-content="empty" %>
<%@attribute name="name" required="true"%>
<%@attribute name="options" required="true" type="java.util.List"%>
<%@attribute name="allowNull" type="java.lang.Boolean"%>
<%@attribute name="selected"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<select size="1" name="${name}" id="${name}">
	<c:if test="${allowNull}">
		<option value=""></option>
	</c:if>
	<c:forEach items="${options}" var="item">
		<option value="${item.id}" ${selected eq item.id ? "selected" : ""}>
			${item.name}
		</option>
	</c:forEach>
</select>
