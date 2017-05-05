import pandas as pd
import matplotlib.plot as plt
import numpy as np


def calc_chi_squared():
    pass


def calc_e():
    predictions = []
    for i in range((1.6*.6), (4*1.6), .1):
        predicted_e = 10**-19 * -1 * i
        predictions.append(predicted_e)
