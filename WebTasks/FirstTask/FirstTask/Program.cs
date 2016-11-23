using System;
using System.Threading.Tasks;

namespace FirstTask
{
    class Program
    {
        static void Main(string[] args)
        {
            
        }

        static void Foo()
        {
            Action<dynamic, dynamic> action = async (dynamic ctx, dynamic cb) =>
            {
                DateTime baseDate;
                int numberOfDays;

                await Task.FromResult<int>(0);

                var baseDateString = ctx.query.b.ToString();
                var numberOfDaysString = ctx.query.n.ToString();

                var baseDateParsed = DateTime.TryParse(baseDateString, out baseDate);
                var numberOfDaysParsed = int.TryParse(numberOfDaysString, out numberOfDays);

                if (!(baseDateParsed && numberOfDaysParsed))
                {
                }

                cb(null, );
            };
        }
    }
}