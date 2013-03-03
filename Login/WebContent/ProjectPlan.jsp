
<!-- jsp koji sluzi za kreiranje ciste html forme za projektni plan -->


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel='stylesheet' href='ProjectPlan.css' type='text/css'>
		<title>Project Plan</title>
	</head>
	<body>
		<jsp:include page="/SecTeamServlet" flush="false" />

		<form  id='form' name='form1' method='get' action='ProjectPlanProcessing'>
			<fieldset>
				<legend>1. Revision</legend>
				<ol>
					<li>
						<label>1.1. Overview:</label><br/>
						<textarea name='overview' rows='6' cols='63' placeholder='Enter any product change since last document revision. If this is your first revision, enter this field empty...'></textarea>
					</li>
				</ol>
				<ol>
					<li>
						<label>1.2. Target Audience:</label><br/>
						<textarea name='targetAudience' rows='6' cols='63' placeholder='Enter target audience or user/group who will be intended to use your application afterwards and product availability...'></textarea>
					</li>
				</ol>
			</fieldset>
			<fieldset>
				<legend>2. Introduction</legend>
				<ol>
					<li>
						<label>2.1. Project Overview:</label><br/>
						<textarea name='projectOverview' rows='6' cols='63' placeholder='Enter product purposes and usability. Describe product summary and your review of finalized product. List software needed for realization and necessary period of time for complete product...'></textarea>
					</li>
				</ol>
				<ol>
					<li>
						<label>2.2. Project Deliverables:</label><br/>
						<textarea name='projectDeliverables' rows='6' cols='63' placeholder='Enter products of project...'></textarea>
					</li>
				</ol>
			</fieldset>
			<fieldset>
				<legend>3. Project Organizations</legend>
				<ol>
					<li>
						<label>3.1. Software Process Model:</label><br/>
						<textarea name='softwareProcessModel' rows='6' cols='63' placeholder='Write informations in detail about software process model and model stages you are using and advantages against other process models in case of your project...'></textarea>
					</li>
				</ol>
				<ol>
					<li>
						<label>3.2. Roles and Responsibilities:</label><br/>
						<textarea name='rolesAndResponsibilities' rows='6' cols='63' placeholder='Describe roles for every team member and every responsibilities for each...'></textarea>
					</li>
				</ol>
				<ol>
					<li>
						<label>3.3. Tools and Techniques:</label><br/>
						<textarea name='toolsAndTechniques' rows='6' cols='63' placeholder='List all tools needed for project and with associated techniques like programming languages, software etc...'></textarea>
					</li>
				</ol>
			</fieldset>
			<fieldset>
				<legend>4. Project Management Plan</legend>
				<ol>
					<li>
						<label>4.1. Tasks:</label><br/>
						<textarea name='tasks' rows='6' cols='63' placeholder='Describe every project task and associate them with team members. Include description, products, deadlines, resources, dependencies and limitations...'></textarea>
					</li>
				</ol>
				<ol>
					<li>
						<label>4.2. Deliverables and Milestones:</label><br/>
						<select>
						</select>
					</li>
				</ol>
				<ol>
					<li>
						<label>4.3. Risks and Contingencies:</label><br/>
						<textarea name='risksAndContingencies' rows='6' cols='63' placeholder='Write all risks and contingencies of the system, their probabilities, influence on the system, mechanism of prevention for each...'></textarea>
					</li>
				</ol>
				<ol>
					<li>
						<label>4.4. Timetable:</label><br/>
						<textarea name='timetable' rows='6' cols='63' placeholder='Describe activity timetable...'></textarea>
						<label>Gantt Chart:&nbsp</label><br/>
						<input type='file' name='ganttchart' />
					</li>
				</ol>
			</fieldset>
			<fieldset>
				<legend>5. Additional Materials</legend>
				<ol>
					<li>
						<label>5.1. References:</label><br/>
						<textarea name='references' rows='6' cols='63' placeholder='Enter all additional materials you used like references to a websites etc...'></textarea>
					</li>
				</ol>
			</fieldset>
			
			<fieldset>
			<span>
				<input type='reset' value='Reset all'/>
				<input type='submit' value='Submit'/>
			</span>
			</fieldset>
		</form>
	</body>
</html>