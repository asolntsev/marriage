<!DOCTYPE html>
<html>
<head>
  <title>Marriage registry</title>
  <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
  <link href="css/marriage.css" rel="stylesheet" media="screen">
</head>

<body>

<div class="container-narrow">

  <div class="masthead">
    <ul class="nav nav-pills pull-right">
      <li><a href="/registration">Register a marriage</a></li>
    </ul>
    <h3 class="muted">Marriage registry</h3>
  </div>
  
  <table id="marriages" class="table table-striped">
    <thead>
      <tr>
        <th>Husband</th>
        <th>Wife</th>
        <th>Registration date</th>
      </tr>
    </thead>
    <tbody>
      <#list marriages as marriage>
        <tr class="marriage">
          <td>${marriage.husband.name}</td>
          <td>${marriage.wife.name}</td>
          <td>${marriage.registeredAt?string["dd.MM.yyyy"]}</td>
        </tr>
      </#list>
    </tbody>
  </table>

  <hr>

  <img src="img/just-married-with-hearts.gif" class="center" style="width: 300px; margin-top: 150px"/>

</div>

<script src="js/jquery-2.0.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>