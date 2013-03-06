
<HTML>
<HEAD>

		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css" />
		<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
		<script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script>
<jsp:include page="/SecServlet" flush="false" />
    <SCRIPT>
        function addRow(tableID)
		{
		
			var table = document.getElementById(tableID);
			var tmp=0;
 
            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);
			tmp=rowCount+1;
			var cell2 = row.insertCell(0);
			cell2.innerHTML = "Answer " + tmp + ": ";
			
            var cell1 = row.insertCell(1);
            var element1 = document.createElement("input");
            element1.type = "text";
			
			element1.name = "answer";
            cell1.appendChild(element1);
			
        }
       
    </SCRIPT>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js" type="text/javascript"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/jquery-ui.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function()
			{
				$("#deadline").datepicker();
			});
		</script>
		<script type='text/javascript'>
		//funkcija za validaciju obrasca na nacin da ne dopusta prazna polja

		function validacija(d)
		{
			if(d.value.length == 0)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
        </script>
         <script type='text/javascript'>
		
		// javascript funkcija za ispitivanje jesu li sva polja ispunjena
        var form = document.getElementById('survey_form');
        form.onsubmit = function(event) {
				if (form['deadline'].value == '' | form['answer'].value == '' | form['question'].value == '')  
				{  
					alert('All fields must be filled');
					event.preventDefault();
				}
        };
        </script>
</HEAD>

<BODY>
<table>
	<tr>
		<td style="background-color:#0066FF; width:1000px; height:10px">
			<h1 style="color:white">Survey - management</h1>
		</td>
	</tr>
	</table>
 
	<form name="survey_form" action="Database" method="get">
	<h3>Your question:</h3>
		<textarea rows="2" cols="50" name="question" onchange='if (!validacija(this))
							{
								document.getElementById("m_question").innerHTML="* (required)";
							}
							else
							{
								document.getElementById("m_question").innerHTML="";
							}'></textarea>
							<i style='color:red;' id='m_description'></i>
							
	<br/>&nbsp;Deadline: &nbsp;
	<input type="text" id="deadline" name="deadline" onchange='if (!validacija(this))
							{
								document.getElementById("m_deadline").innerHTML="* (required)";
							}
							else
							{
								document.getElementById("m_deadline").innerHTML="";
							}'>
							<i style='color:red;' id='m_deadline'></i>
	<br>
	<h3>Your answers:</h3>
    <TABLE id="dataTable">
    <TR>
	
		    <TD> Answer 1: </TD>
            <TD> <INPUT type="text" name="answer" onchange='if (!validacija(this))
							{
								document.getElementById("m_answer").innerHTML="* (required)";
							}
							else
							{
								document.getElementById("m_answer").innerHTML="";
							}'
							/> <i style='color:red;' id='m_answer'></i>  </TD>
           
        </TR>
      
    </TABLE>
	
	    <INPUT type="button" value="Add answer" onclick="addRow('dataTable')" /> 
<br> 
<h3>Choose type of survey:</h3>
 <input type="radio" name="type" value="single" checked/> Single choice
<br />
<input type="radio" name="type" value="multi" /> Multiple choice
<br/>
<input type="submit" value="Submit" />
<input type=button onClick="location.href='http://localhost:8080/Login/survey_index.jsp'" value='Go back'>
</form>

</BODY>
</HTML>
