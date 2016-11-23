/* 
You can access your webtask at the following url:

https://webtask.it.auth0.com/api/run/wt-sathyaish-gmail_com-0/addDays?webtask_no_cache=1

*/

module.exports = function(ctx, cb)
{
	try
	{
		var baseDateString = ctx.query.b;
		var numberOfDaysString = ctx.query.n;
		var usageInstruction = "<br />Usage: url?b={yyyy-mm-dd}&n={numerOfDaysToAdd}";
		
		var baseDate;
		
		if (baseDateString == null || baseDateString === "today" || baseDateString === "now")
		{
			baseDate = new Date();
		}
		else
		{
			baseDate = parseDate(baseDateString);
		}
		
		var numberOfDays = stringToInt(numberOfDaysString);
		
		if (isNaN(numberOfDays))
		{
			cb("Invalid input for number of days to add." + usageInstruction, null);
			return;
		}
			
		var newDate = new Date();
		newDate.setDate(baseDate.getDate() + numberOfDays);
		
		cb(null, newDate.toString());
	}
	catch(error)
	{
		cb(error + usageInstruction, null);
	}
}

function stringToInt(str) {
  var num = parseInt(str);
  if (num == str)
    return num;
  return NaN;
}

function parseDate(input)
{
	var parts = input.split('-');
	return new Date(parts[0], parts[1], parts[2]);
}