/*
Copyleft (C) 2005 H�lio Perroni Filho
xperroni@yahoo.com
ICQ: 2490863

This file is part of ChatterBean.

ChatterBean is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.

ChatterBean is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with ChatterBean (look at the Documents/ directory); if not, either write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307 USA, or visit (http://www.gnu.org/licenses/gpl.txt).
*/

package alicebot.aiml;

import org.xml.sax.Attributes;
import alicebot.AliceBot;
import alicebot.Context;
import alicebot.Match;

public class Id extends TemplateElement
{
  /*
  Constructors
  */

  public Id()
  {
  }

  public Id(Attributes attributes)
  {
  }

  /*
  Methods
  */
  
  public boolean equals(Object obj)
  {
    if (!super.equals(obj))
      return false;
    else
      return toString().equals(obj.toString());
  }
  
  public int hashCode()
  {
    return process(null).hashCode();
  }

  public String process(Match match)
  {
    if (match == null) return "unknown";
    AliceBot bot = match.getCallback();
    Context context = bot.getContext();
    return context.id();
  }
}
