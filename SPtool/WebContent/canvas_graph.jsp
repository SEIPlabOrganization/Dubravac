<!DOCTYPE html>
<html>
<head>
		<link rel="stylesheet" href="CanvasGraph.css" />

</head>
    <body>
	    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.6.1.min.js"></script>
        <script type="text/javascript">
        //document.write(parent.idp);
        //var id;
        //document.write(id);
		
		// Returns the max Y value in our data list
            function getMaxY() {
                var max = 0;
                
                for(var i = 0; i < data.values.length; i ++) {
                    if(data.values[i].Y > max) {
                        max = data.values[i].Y;
                    }
                }
                
                max += 10 - max % 10;
                return max;
            }
            
            // Return the x pixel for a g point
            function getXPixel(val) {
                return ((graph.width() - xPadding-15) / data.values.length) * val + (xPadding * 1.5);
            }
            
            // Return the y pixel for a g point
            function getYPixel(val) {
                return graph.height() - (((graph.height() - yPadding-22) / getMaxY()) * val) - yPadding;
            }
			            
			function drawGraph()
			{
				xPadding = 30;
				yPadding = 20;
				
				data = { values:[
					{ X: "1", Y: 6.25 },
					{ X: "2", Y: 6.25*2 },
					{ X: "3", Y: 6.25*3 },
					{ X: "4", Y: 6.25*4 },
					{ X: "5", Y: 6.25*5 },
					{ X: "6", Y: 6.25*6 },
					{ X: "7", Y: 6.25*7 },
					{ X: "8", Y: 6.25*8 },
					{ X: "9", Y: 6.25*9 },
					{ X: "10", Y: 6.25*10 },
					{ X: "11", Y: 6.25*11 },
					{ X: "12", Y: 6.25*12 },
					{ X: "13", Y: 6.25*13 },
					{ X: "14", Y: 6.25*14 },
					{ X: "15", Y: 6.25*15 },
					{ X: "16", Y: 6.25*16 },
				]};
				
				graph = $('#graph1');
				var c = graph[0].getContext('2d');            
				
				c.lineWidth = 2;
				c.strokeStyle = '#333';
				c.font = 'italic 8pt sans-serif';
				c.textAlign = "center";
				
				// Draw the axises
				c.beginPath();
				c.moveTo(xPadding-5,12);
				c.lineTo(xPadding, 0);
				c.lineTo(xPadding+5, 12);
				c.stroke();
				c.beginPath();
				c.moveTo(xPadding, 0);
				c.lineTo(xPadding, graph.height() - yPadding);
				c.lineTo(graph.width(), graph.height() - yPadding);
				c.lineTo(graph.width()-12, graph.height() - yPadding-5);
				c.stroke();
				c.beginPath();
				c.moveTo(graph.width(), graph.height() - yPadding);
				c.lineTo(graph.width()-12, graph.height() - yPadding+5);
				c.stroke();
				
				// Draw the X value texts
				for(var i = 0; i < data.values.length; i ++) {
					c.fillText(data.values[i].X, getXPixel(i), graph.height() - yPadding + 20);
				}
				c.font = 'bold italic 12pt times-new-roman';
				c.fillText("WEEK", graph.width()-30, graph.height() - yPadding - 10);
				c.font = 'italic 8pt sans-serif';
				
				// Draw the Y value texts
				c.textAlign = "right";
				c.textBaseline = "middle";
				
				for(var i = 0; i < getMaxY(); i += 10) {
					c.fillText(i, xPadding - 10, getYPixel(i));
				}
				c.font = 'bold italic 13pt times-new-roman';
				c.fillText("%", xPadding -10, getYPixel(getMaxY())-10);
				c.font = 'italic 8pt sans-serif';
				
				c.strokeStyle = '#f00';
				
				// Draw the line g
				c.beginPath();
				c.moveTo(getXPixel(0), getYPixel(data.values[0].Y));
				for(var i = 1; i < data.values.length; i ++) {
					c.lineTo(getXPixel(i), getYPixel(data.values[i].Y));
				}
				c.stroke();
				
				// Draw the dots
				c.fillStyle = '#333';
				
				for(var i = 0; i < data.values.length; i ++) {  
					c.beginPath();
					c.arc(getXPixel(i), getYPixel(data.values[i].Y), 3, 0, Math.PI * 2, true);
					c.fill();
				}
			}
        </script>
	
        <canvas id="graph1" width="450" height="250"> Your web browser doesn't support HTML5 canvas element </canvas>
		<script type="text/javascript">
			drawGraph();
		</script>
    </body>
</html>