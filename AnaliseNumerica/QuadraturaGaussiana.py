import math

def quadratura(f, pontos_e_pesos):
    soma = 0
    for x_k, c_k in pontos_e_pesos:
        soma += c_k * f(x_k)
    return soma

def change(f, a, b, u): # transforma uma integral definida num intervalo a,b numa definida no intervalo -1,1
    return f((b+a)/2 + ((b-a)*u)/2)*(b-a)/2

def f(x):
    return ((x**2) + (math.cos(2*x**3)))**2
a, b = [-1.086, 1.17]

def g(u):
    return change(f, a, b, u)

pontos = (-0.33998104358485626, 0.33998104358485626, -0.8611363115940526, 0.8611363115940526)
pesos = (0.6521451548625461, 0.6521451548625461, 0.34785484513745385, 0.34785484513745385)
print(math.sqrt(quadratura(g, zip(pontos,pesos))))

