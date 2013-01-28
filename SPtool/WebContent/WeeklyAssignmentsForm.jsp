<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel='stylesheet' href='style.css' type='text/css'>
		<title>Weekly Assignments</title>
		<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js' type='text/javascript'></script>
		<script src='https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/jquery-ui.min.js' type='text/javascript'></script>
		<script type='text/javascript'>
			$(function()
			{
				$('#date_picker').datepicker();
			});
		</script>
	</head>
	<body>
	<!--<jsp:include page="/SecTeamServlet" flush="false" />-->
		<form id='form' name='form1' method='get' action='WeeklyAssignmentsServlet'>
			<fieldset>
				<legend>Weekly Assignments</legend>
				<ol><li>
					<table border='0'>
						<tr>
							<td>Assignment for:</td>
							<td>
							<jsp:include page="/WeeklyAssignmentsSelectServlet" />
							</td>
						</tr>
						<tr>
							<td>Name:</td>
							<td><input type='text' name='name' placeholder='Enter weekly assignment name...'></td>
						</tr>
						<tr>
							<td>Description:</td>
							<td><textarea rows='5' cols='60' name='description' placeholder='Describe weekly assignment...'></textarea></td>
						</tr>
						<tr>
							<td rowspan='2'>Difficulty:</td>
							<td>
								 &nbsp;&nbsp;1&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5
							</td>
						</tr>
						<tr>
							<td>
								<input type='radio' name='difficulty' value='1' checked='checked'/>
								<input type='radio' name='difficulty' value='2'/>
								<input type='radio' name='difficulty' value='3'/>
								<input type='radio' name='difficulty' value='4'/>
								<input type='radio' name='difficulty' value='5'/>
							</td>
						</tr>
						<tr>
							<td>Week number:</td>
							<td>
								<select name='week'>
									<option value='' selected='selected'></option>
									<option value='1'>1</option>
									<option value='2'>2</option>
									<option value='3'>3</option>
									<option value='4'>4</option>
									<option value='5'>5</option>
									<option value='6'>6</option>
									<option value='7'>7</option>
									<option value='8'>8</option>
									<option value='9'>9</option>
									<option value='10'>10</option>
									<option value='11'>11</option>
									<option value='12'>12</option>
									<option value='13'>13</option>
									<option value='14'>14</option>
									<option value='15'>15</option>
									<option value='16'>16</option>
									<option value='17'>17</option>
									<option value='18'>18</option>
									<option value='17'>19</option>
									<option value='18'>20</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>Deadline:</td>
							<td><input type='text' id='date_picker' name='deadline'></td>
						</tr>
						<tr>
							<td>Product format:</td>
							<td>
								<select name='productFormat'>
									<option value='research' selected='selected'>research</option>
									<option value='document'>document</option>
								</select>
							</td>
						</tr>
					</table>
				</li></ol>
			</fieldset>
			
			<fieldset>
				<input type='submit' value='Submit'/>
			</fieldset>
		</form>
		
		<script type='text/javascript'>
        var form = document.getElementById('form');
        form.onsubmit = function(event) {
				if (form['nameSurname'].value == '' | form['week'].value == '' | form['deadline'].value == '' | form['description'].value == '')  
				{  
					alert('All fields must be filled');
					event.preventDefault();
				}
        };
        </script>
	</body>
</html>