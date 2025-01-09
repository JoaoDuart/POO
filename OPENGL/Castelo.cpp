#include <GL/glew.h>
#include <GLFW/glfw3.h>
#include <GL/glu.h>
#include <cmath>  // Adicionando a biblioteca cmath

void drawCube() {
    GLfloat vertices[] = {
        -0.5f, -0.5f, -0.5f,
         0.5f, -0.5f, -0.5f,
         0.5f,  0.5f, -0.5f,
        -0.5f,  0.5f, -0.5f,
        -0.5f, -0.5f,  0.5f,
         0.5f, -0.5f,  0.5f,
         0.5f,  0.5f,  0.5f,
        -0.5f,  0.5f,  0.5f
    };

    GLubyte indices[] = {
        0, 1, 2, 3,  // back face
        4, 5, 6, 7,  // front face
        0, 1, 5, 4,  // bottom face
        2, 3, 7, 6,  // top face
        0, 3, 7, 4,  // left face
        1, 2, 6, 5   // right face
    };

    glEnableClientState(GL_VERTEX_ARRAY);
    glVertexPointer(3, GL_FLOAT, 0, vertices);
    glDrawElements(GL_QUADS, 24, GL_UNSIGNED_BYTE, indices);
    glDisableClientState(GL_VERTEX_ARRAY);

    // Desenhar linhas pretas ao redor das formas
    glLineWidth(2.0f);  // Aumentar a espessura das linhas do contorno
    glColor3f(0.0f, 0.0f, 0.0f);
    glEnableClientState(GL_VERTEX_ARRAY);
    glVertexPointer(3, GL_FLOAT, 0, vertices);
    glDrawElements(GL_LINE_LOOP, 24, GL_UNSIGNED_BYTE, indices);
    glDisableClientState(GL_VERTEX_ARRAY);
}

void drawCone() {
    GLUquadric* quad = gluNewQuadric();
    gluCylinder(quad, 0.5, 0.0, 1.0, 32, 32);
    gluDeleteQuadric(quad);
}

void drawRoundedDoor() {
    int num_segments = 100;
    float radius = 0.5f;
    float height = 1.0f;

    glBegin(GL_POLYGON);
    for (int i = 0; i <= num_segments; ++i) {
        float theta = 2.0f * 3.1415926f * float(i) / float(num_segments) / 2.0f; // dividir por 2 para meia circunferência
        float x = radius * cosf(theta);
        float y = radius * sinf(theta);
        glVertex3f(x, y + height / 2, 0.5f);  // Ajuste na profundidade da porta
    }
    glVertex3f(-radius, -height / 2, 0.5f);
    glVertex3f(radius, -height / 2, 0.5f);
    glEnd();
}

void drawBase() {
    // Desenha o fundo da tigela
    glColor3f(0.0f, 1.0f, 0.0f);  // Marrom
    glPushMatrix();
    glTranslatef(0.0f, -0.5f, 0.0f);
    glScalef(4.0f, 0.2f, 4.0f);
    drawCube();
    glPopMatrix();

    // Desenha as paredes da tigela
    for (int i = -1; i <= 1; i += 2) {
        glColor3f(0.5f, 0.25f, 0.1f);
        glPushMatrix();
        glTranslatef(i * 2.0f, 0.0f, 0.0f);
        glScalef(0.4f, 1.5f, 4.4f);  // Aumentar a espessura das paredes
        drawCube();
        glPopMatrix();

        glColor3f(0.5f, 0.25f, 0.1f);
        glPushMatrix();
        glTranslatef(0.0f, 0.0f, i * 2.0f);
        glScalef(4.4f, 1.5f, 0.4f);  // Aumentar a espessura das paredes
        drawCube();
        glPopMatrix();
    }
}


void drawCastle() {
    // Desenha a base
    drawBase();

    // Porta no meio da base
    glColor3f(0.3f, 0.15f, 0.1f);  // Marrom escuro para a porta
    glPushMatrix();
    glTranslatef(0.2f, -0.3f, 1.71);  // Ajuste da posição da porta
    drawRoundedDoor();
    glPopMatrix();

    // Torres e telhados
    for (int i = -1; i <= 1; i += 2) {
        for (int j = -1; j <= 1; j += 2) {
            // Corpo da torre
            glColor3f(0.5f, 0.25f, 0.1f);  // Vermelho escuro para as torres
            glPushMatrix();
            glTranslatef(i * 2.0f, 1.0f, j * 2.0f);  // Ajuste a altura da torre
            glScalef(0.5f, 1.0f, 0.5f);
            drawCube();
            glPopMatrix();

            // Telhado da torre
            glColor3f(0.8f, 0.1f, 0.1f);  // Marrom para os telhados
            glPushMatrix();
            glTranslatef(i * 2.0f, 1.4f, j * 2.0f);  // Ajuste a altura do telhado
            glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
            drawCone();
            glPopMatrix();
        }
    }
}

int main() {
    if (!glfwInit()) {
        return -1;
    }

    GLFWwindow* window = glfwCreateWindow(800, 600, "3D Castle", NULL, NULL);
    if (!window) {
        glfwTerminate();
        return -1;
    }

    glfwMakeContextCurrent(window);
    glewExperimental = GL_TRUE;
    glewInit();

    glClearColor(0.0f, 0.75f, 1.0f, 1.0f);  // Céu azul
    glEnable(GL_DEPTH_TEST);

    while (!glfwWindowShouldClose(window)) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        // Configurar a câmera
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(45.0, 800.0 / 600.0, 0.1, 50.0);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        gluLookAt(4.0, 3.0, 4.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);

        // Desenhar o chão verde
        glBegin(GL_QUADS);
        glColor3f(0.0f, 1.0f, 0.0f);  // Verde
        glVertex3f(-10.0f, -0.5f, -10.0f);
        glVertex3f(10.0f, -0.5f, -10.0f);
        glVertex3f(10.0f, -0.5f, 10.0f);
        glVertex3f(-10.0f, -0.5f, 10.0f);
        glEnd();

        drawCastle();

        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    glfwTerminate();
    return 0;
}
