/**************************************************************************
   Copyright 2012 Brandon Borkholder

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 ***************************************************************************/

package glg2d.impl.shader;

import glg2d.GLGraphics2D;
import glg2d.impl.gl2.GL2Transformhelper;
import glg2d.impl.gl2.GL2ColorHelper;
import glg2d.impl.gl2.GL2StringDrawer;

public class GLShaderGraphics2D extends GLGraphics2D {
  @Override
  protected void createDrawingHelpers() {
    Shader s = new ResourceShader(GLShaderGraphics2D.class, "TextureShader.v", "TextureShader.f");
    imageHelper = new G2DShaderImageDrawer(s);
    stringHelper = new GL2StringDrawer();
    matrixHelper = new GL2Transformhelper();
    colorHelper = new GL2ColorHelper();

    s = new ResourceShader(GLShaderGraphics2D.class, "FixedFuncShader.v", "FixedFuncShader.f");
    shapeHelper = new G2DShaderShapeDrawer(s);

    addG2DDrawingHelper(imageHelper);
    addG2DDrawingHelper(stringHelper);
    addG2DDrawingHelper(shapeHelper);
    addG2DDrawingHelper(matrixHelper);
    addG2DDrawingHelper(colorHelper);
  }
}