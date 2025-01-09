#include <stdio.h>  // this library is for standard input and output
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include <GL/glut.h>/// this library is for glut the OpenGL Utility Toolkit

#define RAINSIZE 50
int winWidth = 1000, winHeight = 1000;
int counter = 0;
time_t t;
float rotationAngle = 0;

struct drop {
    float x = 400;
    float y = 400;
    float inc = 0.01;
    float radius = 5;
    float scale = 1.0;
    float rotationAngle = 0;
    float rotationInc = 1;
};

drop rain[RAINSIZE];

void initRain() {
    srand((unsigned)time(&t));
    for (int i = 0; i < RAINSIZE; i++) {
        rain[i].x = rand() % winWidth;
        rain[i].y = rand() % winHeight;
        rain[i].inc = 1.5 + (float)(rand() % 100) / 1000.0;
        rain[i].radius = (float)(rand() % 8);
        rain[i].scale = (float)(rand() % 20000) / 1000.0;
        rain[i].rotationAngle = (float)(rand() % 3000) / 1000.0;
        rain[i].rotationInc = (float)(rand() % 100) / 1000.0;
        if ((rand() % 100) > 50) {
            rain[i].rotationInc = -rain[i].rotationInc;
        }
    }
}

void drawParticleShape(int i) {
    glBegin(GL_POINTS);
    glVertex2d(rain[i].x, rain[i].y);
    glEnd();
    glBegin(GL_LINES);
    glVertex2d(rain[i].x, rain[i].y);
    glVertex2d(rain[i].x, rain[i].y + rain[i].radius * 2);
    glEnd();
}

void drawDrop(int i) {
    glColor3f(0.0, 0.0, 1.0);
    glLineWidth(2);
    drawParticleShape(i);
    rain[i].y -= rain[i].inc;
    if (rain[i].y < 0) {
        rain[i].y = winHeight;
    }
}

void drawRain() {
    for (int i = 0; i < RAINSIZE; i++) {
        drawDrop(i);
    }
}

void init() {
    glClearColor(0.0, 0.0, 0.0, 0.0); 
    glMatrixMode(GL_PROJECTION); 
    gluOrtho2D(0.0, winWidth, 0.0, winHeight);
}

float elapsedTime = 0, base_time = 0, fps = 0, frames;

void calcFPS() {
    elapsedTime = glutGet(GLUT_ELAPSED_TIME);
    if ((elapsedTime - base_time) > 1000.0) {
        fps = frames * 1000.0 / (elapsedTime - base_time);
        printf("fps: %f", fps);
        base_time = elapsedTime;
        frames = 0;
    }
    frames++;
}

void display() {
   glClear(GL_COLOR_BUFFER_BIT);
   drawRain();
   calcFPS();
   glFlush();
   counter++;
   glutPostRedisplay();
}

void idle() {
}
int main(int argc, char** argv) {
    srand(1);
    initRain();
    glutInit(&argc, argv);  
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGBA); 
    glutInitWindowSize(winWidth, winHeight); 
    glutInitWindowPosition(0, 0); 
    glutCreateWindow("Hello"); 
    glEnable(GL_BLEND);
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    init(); 
    glutIdleFunc(idle); 
    glutDisplayFunc(display); 
    
    glutMainLoop();
    return 0;
}