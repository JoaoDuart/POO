#include <GL/glut.h>
#include <cmath> // Inclui a biblioteca cmath para funções matemáticas

float walkAngle = 0.0f;
float waveAngle = 0.0f;
bool walking = false;
bool waving = false;
bool waveDirection = true;

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
    glRotatef(30 + waveAngle, 0.0, 0.0, 1.0); // Rotaciona o braço esquerdo
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
    glRotatef(10 + (walking ? sin(walkAngle) * 15 : 0), 1.0, 0.0, 0.0); // Rotaciona a perna esquerda com movimento de caminhada
    glScalef(0.2, 1.0, 0.2);
    drawCube(1.0);
    glPopMatrix();

    // Perna direita
    glPushMatrix();
    glColor3f(0.0, 0.0, 1.0); // Perna azul
    glTranslatef(0.25, -1.0, 0.0); // Posiciona a perna à direita
    glRotatef(-10 + (walking ? sin(walkAngle + 3.14) * 15 : 0), 1.0, 0.0, 0.0); // Rotaciona a perna direita com movimento de caminhada
    glScalef(0.2, 1.0, 0.2);
    drawCube(1.0);
    glPopMatrix();
}
void update(int value) {
    if (walking) {
        walkAngle += 0.1f;
    }

    if (waving) {
        if (waveDirection) {
            waveAngle += 2.0f;
            if (waveAngle >= 45.0f) {
                waveDirection = false;
            }
        } else {
            waveAngle -= 2.0f;
            if (waveAngle <= 0.0f) {
                waveDirection = true;
                waving = false; // Para o aceno após completar o movimento
            }
        }
    }

    glutPostRedisplay();
    glutTimerFunc(16, update, 0);
}
void display() {
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glLoadIdentity();

    // Configurando a câmera mais próxima do robô
    gluLookAt(3.0, 3.0, 7.0,   // Posição da câmera para uma visão mais próxima
              0.0, 0.0, 0.0,    // Para onde a câmera aponta
              0.0, 1.0, 0.0);   // Direção do "up"

    // Desenha o robô
    drawRobot();

    glutSwapBuffers();
}

void keyboard(unsigned char key, int x, int y) {
    if (key == 'a') {
        walking = !walking; // Alterna entre caminhar e parar
    } else if (key == 'w') {
        if (!waving) {
            waving = true; // Inicia o aceno
            waveAngle = 0.0f; // Reinicia o ângulo do aceno
            waveDirection = true; // Reinicia a direção do aceno
        }
    }
}
int main(int argc, char** argv) {
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
    glutInitWindowSize(800, 600);
    glutCreateWindow("Simple 3D Robot");

    init();

    glutDisplayFunc(display);
    glutReshapeFunc(reshape);
    glutKeyboardFunc(keyboard);
    glutTimerFunc(16, update, 0);

    glutMainLoop();

    return 0;
}
