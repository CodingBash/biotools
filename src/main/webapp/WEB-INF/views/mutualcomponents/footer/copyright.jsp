<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
<p class="text-center">
	Copyright <span class="glyphicon glyphicon-copyright-mark"></span> ${year}. BioTools.com. All Rights
	Reserved
</p>