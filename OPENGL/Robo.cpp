#include <GL/glut.h>

void init() {
    glClearColor(0.529, 0.808, 0.980, 1.0); // Fundo azul claro
    glEnable(GL_DEPTH_TEST);
}

void reshape(int w, int h) {
    glViewport(0, 0, w, h);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective(45.0, (double)w / (double)h, 1.0, 200.0);
    glMatrixMode(GL_MODELVIEW);
}
void drawCube(float size) {
    glutSolidCube(size);
}

void drawSphere(float radius) {
    glutSolidSphere(radius, 20, 20);
}

void drawRobot() {
    // Corpo
    glPushMatrix();
    glColor3f(1.0, 0.0, 0.0); // Corpo vermelho
    glScalef(1.0, 1.5, 0.5); // Redimensiona o cubo para ser o corpo
    drawCube(1.0);
    glPopMatrix();

    // Cabeça
    glPushMatrix();
    glColor3f(1.0, 0.84, 0.0); // Cabeça dourada
    glTranslatef(0.0, 1.25, 0.0); // Posiciona a cabeça acima do corpo
    drawSphere(0.5);
    glPopMatrix();

    // Braço esquerdo
    glPushMatrix();
    glColor3f(0.0, 1.0, 0.0); // Braço verde
    glTranslatef(-0.75, 0.5, 0.0); // Posiciona o braço à esquerda do corpo
    glRotatef(30, 0.0, 0.0, 1.0); // Rotaciona o braço esquerdo
    glScalef(0.2, 1.0, 0.2); // Redimensiona o cubo para ser o braço
    drawCube(1.0);
    glPopMatrix();

    // Braço direito
    glPushMatrix();
    glColor3f(0.0, 1.0, 0.0); // Braço verde
    glTranslatef(0.75, 0.5, 0.0); // Posiciona o braço à direita do corpo
    glRotatef(-30, 0.0, 0.0, 1.0); // Rotaciona o braço direito
    glScalef(0.2, 1.0, 0.2);
    drawCube(1.0);
    glPopMatrix();

    // Perna esquerda
    glPushMatrix();
    glColor3f(0.0, 0.0, 1.0); // Perna azul
    glTranslatef(-0.25, -1.0, 0.0); // Posiciona a perna à esquerda
    glRotatef(10, 1.0, 0.0, 0.0); // Rotaciona a perna esquerda
    glScalef(0.2, 1.0, 0.2);
    drawCube(1.0);
    glPopMatrix();

    // Perna direita
    glPushMatrix();
    glColor3f(0.0, 0.0, 1.0); // Perna azul
    glTranslatef(0.25, -1.0, 0.0); // Posiciona a perna à direita
    glRotatef(-10, 1.0, 0.0, 0.0); // Rotaciona a perna direita
    glScalef(0.2, 1.0, 0.2);
    drawCube(1.0);
    glPopMatrix();
}
void display() {
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glLoadIdentity();

    // Configurando a câmera
    gluLookAt(3.0, 3.0, 7.0,  // Posição da câmera para um ângulo
              0.0, 0.0, 0.0,    // Para onde a câmera aponta
              0.0, 1.0, 0.0);   // Direção do "up"

    // Desenha o robô
    drawRobot();

    glutSwapBuffers();
}

int main(int argc, char** argv) {
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
    glutInitWindowSize(800, 600);
    glutCreateWindow("Simple 3D Robot");

    init();

    glutDisplayFunc(display);
    glutReshapeFunc(reshape);
    glutMainLoop();

    return 0;
}
