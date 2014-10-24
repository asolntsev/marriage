<!DOCTYPE html>
<html>
<head>
  <title>Marriage registry: registration</title>
  <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
  <link href="css/jquery-ui.min.css" rel="stylesheet" media="screen">
  <link href="css/marriage.css" rel="stylesheet" media="screen">
</head>
<body>
<div class="container">

  <div class="alert-error">${error!}</div>
  
  <form class="form-signin" method="post">
    <table class="table">
      <thead>
        <tr>
          <th>Husband</th>
          <th></th>
          <th>Wife</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>
            <input name="husbandCode" value="${husband!}" type="text" class="person input-block-level" placeholder="person code" autofocus="">
          </td>
          <td style="text-align: center; font-size: 26px;">+</td>
          <td>
            <input name="wifeCode" value="${wife!}" type="text" class="person input-block-level" placeholder="person code">
          </td>
        </tr>
      </tbody>
    </table>

    <button class="btn btn-large btn-primary center" type="submit">Register</button>
  </form>

</div>

<script src="js/jquery-2.1.1.min.js"></script>
<script src="js/jquery-ui-1.11.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/registration.js"></script>
</body>
</html>