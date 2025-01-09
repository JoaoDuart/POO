#include <GL/glut.h>
#include <cmath>

// Função para desenhar um círculo preenchido
void drawCircle(float radius, float x, float y) {
    glBegin(GL_TRIANGLE_FAN);
    glVertex2f(x, y); // Centro do círculo
    for (int i = 0; i <= 100; ++i) {
        float angle = 2.0f * 3.1415926f * i / 100; // 360 graus divididos em 100 segmentos
        float dx = radius * cosf(angle);
        float dy = radius * sinf(angle);
        glVertex2f(x + dx, y + dy);
    }
    glEnd();
}

// Função para desenhar um retângulo
void drawRectangle(float x1, float y1, float x2, float y2) {
    glBegin(GL_QUADS);
    glVertex2f(x1, y1);
    glVertex2f(x2, y1);
    glVertex2f(x2, y2);
    glVertex2f(x1, y2);
    glEnd();
}

void display() {
    glClear(GL_COLOR_BUFFER_BIT);

    // Desenhar corpo do boneco de neve
    glColor3f(1.0, 1.0, 1.0); // Branco
    drawCircle(0.3f, 0.0f, -0.5f); // Corpo inferior
    drawCircle(0.2f, 0.0f, -0.2f); // Corpo do meio
    drawCircle(0.1f, 0.0f, 0.0f);  // Cabeça

    // Desenhar olhos
    glColor3f(0.0, 0.0, 0.0); // Preto
    drawCircle(0.02f, -0.03f, 0.05f); // Olho esquerdo
    drawCircle(0.02f, 0.03f, 0.05f);  // Olho direito

    // Desenhar nariz
    glColor3f(1.0, 0.5, 0.0); // Laranja
    glBegin(GL_TRIANGLES);
    glVertex2f(0.0f, 0.0f);
    glVertex2f(0.05f, 0.0f);
    glVertex2f(0.025f, -0.05f);
    glEnd();

    // Desenhar boca
    glColor3f(0.0, 0.0, 0.0); // Preto
    glBegin(GL_LINE_STRIP);
    glVertex2f(-0.05f, -0.05f);
    glVertex2f(-0.03f, -0.07f);
    glVertex2f(-0.01f, -0.08f);
    glVertex2f(0.01f, -0.08f);
    glVertex2f(0.03f, -0.07f);
    glVertex2f(0.05f, -0.05f);
    glEnd();

    // Desenhar chapéu
    glColor3f(0.0, 0.0, 0.0); // Preto
    drawRectangle(-0.1f, 0.1f, 0.1f, 0.15f);  // Aba do chapéu
    drawRectangle(-0.05f, 0.15f, 0.05f, 0.25f); // Parte de cima do chapéu

    // Desenhar botões
    glColor3f(0.0, 0.0, 0.0); // Preto
    drawCircle(0.03f, 0.0f, -0.3f); // Botão no corpo do meio
    drawCircle(0.03f, 0.0f, -0.4f); // Botão no corpo do meio
    drawCircle(0.03f, 0.0f, -0.6f); // Botão no corpo inferior
    drawCircle(0.03f, 0.0f, -0.7f); // Botão no corpo inferior

    glFlush();
}

void init() {
    glClearColor(0.0, 0.7, 1.0, 1.0); // Cor de fundo (azul claro)
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(-1.0, 1.0, -1.0, 1.0);
}

int main(int argc, char** argv) {
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
    glutInitWindowSize(500, 500);
    glutCreateWindow("Boneco de Neve Simples");
    init();
    glutDisplayFunc(display);
    glutMainLoop();
    return 0;
}