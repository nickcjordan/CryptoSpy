<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>CryptoSpy</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/mdb.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
	
<body>
	    
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="section">
	<div class="center">
		<h3>
			<strong>CryptoSpy</strong>&nbsp;&nbsp;
			<c:choose>
				<c:when test="${profit > 0}"><td class="" style="color:green"><fmt:formatNumber value = "${profit}" type = "currency"/></td></c:when>
				<c:otherwise><td class="" style="color:red"><fmt:formatNumber value = "${profit}" type = "currency"/></td></c:otherwise>
			</c:choose>
		</h3>
	</div>
		<table class="table table-sm table-striped header-fixed positions">
			<thead class="thead-inverse">
				<tr>
						<th class="id-3">Coin</th>
						<th class="id-3">Sym</th>
						<th class="id-3">#</th>
						<th class="id-3">USD</th>
						<th class="id-3">init USD</th>
						<th class="id-3">now USD</th>
						<th class="id-3">diff USD</th>
						<th class="id-3">BTC</th>
						<th class="id-3">init BTC</th>
						<th class="id-3">now BTC</th>
						<th class="id-3">diff BTC</th>
				</tr>
			</thead>
			<tbody>
				
			<c:forEach items="${coins}" var="coin">
				<c:if test="${coin.quantity > 0}">
				<tr>
					<td class="id-3">${coin.name}</td>
					 <fmt:formatNumber value = "${balance}" type = "currency"/>
					<td class="id-3">${coin.symbol.getSymbol()}</td>
					<td class="id-3"><fmt:formatNumber value = "${coin.quantity}" type = "number" maxFractionDigits = "5"/></td>
					<td class="id-3"><fmt:formatNumber value = "${coin.currentUnitPriceUSD}" type = "currency"/></td>
					<td class="id-3"><fmt:formatNumber value = "${coin.initialValueUSD}" type = "currency"/></td>
					<td class="id-3"><fmt:formatNumber value = "${coin.currentValueUSD}" type = "currency"/></td>
					
					<c:choose>
						<c:when test="${(coin.currentValueUSD - coin.initialValueUSD) > 0}"><td class="" style="color:green"><strong><fmt:formatNumber value = "${coin.currentValueUSD - coin.initialValueUSD}" type = "currency"/></strong></td></c:when>
						<c:otherwise><td class="" style="color:red"><strong><fmt:formatNumber value = "${coin.currentValueUSD - coin.initialValueUSD}" type = "currency"/></strong></td></c:otherwise>
					</c:choose>
					
					<%-- <td class="id-3"><fmt:formatNumber value = "${coin.currentValueUSD - coin.initialValueUSD}" type = "currency"/></td> --%>
					<td class="id-3"><fmt:formatNumber value = "${coin.currentUnitPriceBTC}" type = "number" maxFractionDigits = "6"/></td>
					<td class="id-3"><fmt:formatNumber value = "${coin.initialValueBTC}" type = "number" maxFractionDigits = "6"/></td>
					<td class="id-3"><fmt:formatNumber value = "${coin.currentValueBTC}" type = "number" maxFractionDigits = "6"/></td>
					<td class="id-3"><fmt:formatNumber value = "${coin.currentValueBTC - coin.initialValueBTC}" type = "number" maxFractionDigits = "6"/></td>
				</tr>
				</c:if>
			
			</c:forEach>
				
			</tbody>
		</table>
</div>
		
	    
    <!-- JQuery -->
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="js/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="js/mdb.min.js"></script>
	    
</body>
</html>

