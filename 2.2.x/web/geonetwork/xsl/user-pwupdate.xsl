<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:include href="main.xsl"/>

	<!--
	additional scripts
	-->
	<xsl:template mode="script" match="/">
		<script type="text/javascript" language="JavaScript">
			
			function doUpdate()
			{
				var invalid = " "; // Invalid character is a space
				var minLength = 6; // Minimum length
	
				var pw1 = document.userupdateform.newPassword.value;
				var pw2 = document.userupdateform.newPassword2.value;
	
				// check for a value in both fields.
				if (pw1 == '' || pw2 == '')
				{
					alert('Please enter your new password twice.');
					return;
				}
				// check for bad password confirmation
				if (pw1 != pw2)
				{
					alert ("You did not enter the same new password twice. Please re-enter your password.");
					return;
				}
				// check for minimum length of new password
				if (pw1.length &lt; minLength)
				{
					alert('Your password must be at least ' + minLength + ' characters long. Try again.');
					return;
				}
				// check for spaces
				if (pw1.indexOf(invalid) &gt; -1)
				{
					alert("Sorry, spaces are not allowed.");
					return;
				}
				// all ok, proceed
				document.userupdateform.submit();
			}//update
			
	</script>
	</xsl:template>
	
	<!--
	page content
	-->
	<xsl:template name="content">
		<xsl:call-template name="formLayout">
			<xsl:with-param name="title">
				<xsl:value-of select="/root/gui/strings/userPw"/>
			</xsl:with-param>
			<xsl:with-param name="content">
				<xsl:call-template name="form"/>
			</xsl:with-param>
			<xsl:with-param name="buttons">
				<button class="content" onclick="goBack()"><xsl:value-of select="/root/gui/strings/back"/></button>
				&#160;
				<button class="content" onclick="doUpdate()"><xsl:value-of select="/root/gui/strings/save"/></button>
			</xsl:with-param>
		</xsl:call-template>
	</xsl:template>
	
	<!--
	form
	-->
	<xsl:template name="form">
		<form name="userupdateform" accept-charset="UTF-8" action="{/root/gui/locService}/user.pwupdate" method="post">
			<input type="submit" style="display: none;" />
			<table>
				<tr>
					<th class="padded"><xsl:value-of select="/root/gui/strings/password"/></th>
					<td class="padded"><input class="content" type="password" name="password" value="{/root/response/record/password}"/></td>
				</tr>
				<tr>
					<th class="padded"><xsl:value-of select="/root/gui/strings/newPassword"/></th>
					<td class="padded"><input class="content" type="password" name="newPassword"/></td>
				</tr>
				<tr>
					<th class="padded"><xsl:value-of select="/root/gui/strings/confirmNewPassword"/></th>
					<td class="padded"><input class="content" type="password" name="newPassword2"/></td>
				</tr>
			</table>
		</form>
	</xsl:template>

</xsl:stylesheet>

