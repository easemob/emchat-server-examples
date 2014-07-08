using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;

namespace uLinkAppConsole
{
    class Program
    {
        static void Main(string[] args)
        {
            HXComm.SampleUse mySample = new HXComm.SampleUse();
            mySample.Test("", "", "", "");

            Console.ReadKey();
        }
    }

}
